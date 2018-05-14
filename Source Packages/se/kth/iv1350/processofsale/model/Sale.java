package se.kth.iv1350.processofsale.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import se.kth.iv1350.processofsale.integration.*;

/**
 * This class contains everything a sale should contain and methods to perform a
 * sale.
 */

public class Sale {
	private static Sale instance = null;
	private List<Item> enteredItems = new ArrayList<>();
	private CashRegister cashRegister;
	private Costs costs = new Costs();
	private RegistryCreator creator;
	private CashPayment cashPayment;
	private String date;

	private Sale(CashRegister cashRegister, RegistryCreator creator) {
		this.cashRegister = cashRegister;
		this.creator = creator;
		recordDate();
	}
	
	public static Sale getSale (CashRegister cashRegister, RegistryCreator creator) {
		if (Sale.instance == null) {
			Sale.instance = new Sale (cashRegister, creator);
		}
		return Sale.instance;
	}
	
	public static void endSale() {
		Sale.instance = null;
	}

	/**
	 * The enterItem-operation. This will update the sale with the item that has the
	 * given item identifier, store it in the list as an {@link Item}-object or
	 * increase the quantity of the object by one if it is already stored and update
	 * the running total.
	 * 
	 * @param itemIdentifier
	 *            Unique for an {@link ItemDTO}.
	 * @return a {@link ItemDTO} if one was found from the given item identifier.
	 *         Else it returns <code>null</code>.
	 * @throws InvalidIdentifierException
	 *             when no {@link ItemDTO} could be found with the given item
	 *             identifier.
	 */
	public ItemDTO enterItem(int itemIdentifier) throws InvalidIdentifierException {
		ItemDTO foundItemDTO = getItemDTO(itemIdentifier);
		Item newItem = new Item(foundItemDTO);
		updateSale(newItem);
		return foundItemDTO;
	}

	/**
	 * The enterItems-operation. This will update the sale with the correct item and
	 * the quantity of it.
	 * 
	 * @param itemIdentifier
	 *            Unique for an {@link ItemDTO}.
	 * @param quantity
	 *            Quantity of items of the same sort that is entered.
	 * @return a {@link ItemDTO} if it has the given item identifier. Else it
	 *         returns <code>null</code>.
	 * @throws InvalidIdentifierException
	 *             when no {@link ItemDTO} could be found with the given item
	 *             identifier.
	 */
	public ItemDTO enterItems(int itemIdentifier, int quantity) throws InvalidIdentifierException {
		ItemDTO foundItemDTO = getItemDTO(itemIdentifier);
		Item newItem = new Item(foundItemDTO, quantity);
		updateSale(newItem);
		return foundItemDTO;
	}

	private ItemDTO getItemDTO(int itemIdentifier) throws InvalidIdentifierException {
		ItemRegistry itemReg = creator.getItemReg();
		ItemDTO foundItemDTO = itemReg.findItem(itemIdentifier);
		return foundItemDTO;
	}

	private void updateSale(Item newItem) {
		this.costs.increaseRunningTotal(newItem);
		updateItems(newItem);
	}

	private void updateItems(Item newItem) {
		if (!isAlreadyEntered(newItem)) {
			this.enteredItems.add(newItem);
		} else {
			for (int i = 0; i < this.enteredItems.size(); i++) {
				if (this.enteredItems.get(i).equals(newItem)) {
					this.enteredItems.get(i).increaseQuantity(newItem.getQuantity());
				}
			}
		}
	}

	private boolean isAlreadyEntered(Item newItem) {
		return this.enteredItems.contains(newItem);
	}

	private void recordDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		this.date = dateFormat.format(date).toString();
	}

	/**
	 * @return the date and time
	 */
	public String getDate() {
		return this.date;
	}

	/**
	 * @return the running total of this sale.
	 */
	public double getRunningTotal() {
		return this.costs.getRunningTotal();
	}

	/**
	 * @return the total cost of this sale with taxes included.
	 */
	public double getTotal() {
		double totalCost = this.costs.getTotalCost();
		return totalCost;
	}

	/**
	 * Calculates if the given payment is valid for this sale and updates the cash
	 * register for this sale.
	 * 
	 * @param paidAmount
	 *            The paid amount from the customer.
	 * @return the change after the payment.
	 */
	public double pay(double paidAmount) {
		double totalCost = getTotal();
		if (this.cashPayment == null) {
			this.cashPayment = new CashPayment(paidAmount, totalCost, this.cashRegister);
		} else {
			this.cashPayment.addPaidAmount(paidAmount);
		}
		double change = this.cashPayment.getChange();
		if (change < 0) {
			return change;
		}
		this.cashPayment.updateCashRegister();
		return change;
	}

	/**
	 * @return the created {@link Receipt} for the sale.
	 */
	public Receipt getReceipt() {
		Receipt receipt = new Receipt(enteredItems, date, costs, cashPayment);
		return receipt;

	}

}
