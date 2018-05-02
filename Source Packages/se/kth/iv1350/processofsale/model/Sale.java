package se.kth.iv1350.processofsale.model;

import se.kth.iv1350.processofsale.integration.RegistryCreator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import se.kth.iv1350.processofsale.integration.*;

/**
 * This class contains everything a sale should contain and methods to perform a
 * sale.
 */

public class Sale {
	private List<Item> enteredItems = new ArrayList<>();
	private CashRegister cashRegister;
	private Costs costs = new Costs();
	private RegistryCreator creator;
	private CustomerDTO customer;
	private CashPayment cashPayment;
	private String date;

	/**
	 * Creates a new instance.
	 * 
	 * @param cashRegister
	 *            The cash register that the sale will use and update.
	 * @param creator
	 *            The registry creator that is needed to get the important
	 *            registers.
	 */
	public Sale(CashRegister cashRegister, RegistryCreator creator) {
		this.cashRegister = cashRegister;
		this.creator = creator;
		recordDate();
	}

	/**
	 * The enterItem-operation. This will update the sale with the item that has the
	 * given item identifier, store it in the list as an <code>Item</code> -object
	 * or increase the quantity of the object by one if it is already stored and
	 * update the running total.
	 * 
	 * @param itemIdentifier
	 *            Unique for an <code>ItemDTO</code>.
	 * @return the <code>ItemDTO</code> that was found from the given item
	 *         identifier.
	 * @throws InvalidIdentifierException
	 *             if no <code>ItemDTO</code> with the given item identifier could
	 *             be found.
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
	 *            Unique for an <code>ItemDTO</code>.
	 * @param quantity
	 *            Quantity of items of the same sort that is entered.
	 * @return the <code>ItemDTO</code> with the given item item identifier.
	 * @throws InvalidIdentifierException
	 *             if no <code>ItemDTO</code> with the given item identifier could
	 *             be found.
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
	 * @return the running total of this sale which is stored in <code>Costs</code>.
	 */
	public double getRunningTotal() {
		return this.costs.getRunningTotal();
	}

	/**
	 * @return the total cost.
	 */
	public double getTotal() {
		double totalCost = this.costs.getTotalCost();
		return totalCost;
	}

	/**
	 * Updates the sale with a discount if a customer is found with the given ID
	 * number.
	 * 
	 * @param id
	 *            ID number as a <code>String</code>.
	 * @return the updated total cost for the sale.
	 * @throws InvalidIdentifierException
	 *             if no <code>CustomerDTO</code> with the given ID could be found.
	 */
	public double discountRequest(String id) throws InvalidIdentifierException {
		CustomerDTO customer = getCustomerDTO(id);
		this.costs.enterDiscount(customer);
		this.customer = customer;
		return this.costs.getTotalCost();
	}

	private CustomerDTO getCustomerDTO(String id) throws InvalidIdentifierException {
		CustomerRegistry customerReg = creator.getCustomerReg();
		CustomerDTO foundCustomer = customerReg.findCustomer(id);
		return foundCustomer;
	}

	/**
	 * Calculates if the given payment is valid for this sale and updates the cash
	 * register for this sale.
	 * 
	 * @param paidAmount
	 *            The paid amount from the customer.
	 * @return the change after the valid payment.
	 * @throws InvalidAmountException
	 *             if the given amount is not enough.
	 */
	public double pay(double paidAmount) throws InvalidAmountException {
		double totalCost = getTotal();
		if (this.cashPayment == null) {
			this.cashPayment = new CashPayment(paidAmount, totalCost, this.cashRegister);
		} else {
			this.cashPayment.addPaidAmount(paidAmount);
		}
		double change = this.cashPayment.getChange();
		if (change < 0) {
			throw new InvalidAmountException("Need more payment. Current balance:", change);
		}
		this.cashPayment.updateCashRegister();
		return change;
	}

	/**
	 * @return the created receipt of the sale.
	 * @throws InvalidAmountException
	 *             In case the paid amount for this sale is less than the total
	 *             cost.
	 */
	public Receipt getReceipt() throws InvalidAmountException {
		if (validPayment()) {
			Receipt receipt = new Receipt(customer, enteredItems, date, costs, cashPayment);
			return receipt;
		}
		throw new InvalidAmountException("Need more payment to create receipt. Current balance: ",
				this.cashPayment.getChange());
	}

	private boolean validPayment() throws InvalidAmountException {
		if (this.cashPayment == null) {
			throw new InvalidAmountException("Need payment to create receipt.", getTotal());
		} else if (this.cashPayment.getPaidAmount() >= getTotal()) {
			return true;
		} else {
			return false;
		}

	}

	private void updateAccounting(Receipt receipt) {
		AccountingRegistry accountingReg = this.creator.getAccountingReg();
		// Do something with receipt
	}

	private void updateInventory() {
		InventoryRegistry inventoryReg = this.creator.getInventoryReg();
		// Do something with the item list.
	}
}
