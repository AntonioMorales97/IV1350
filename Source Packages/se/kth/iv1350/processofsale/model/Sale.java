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
	private List<Item> enteredItems = new ArrayList<>();
	private CashRegister cashRegister;
	private Costs costs = new Costs();
	private RegistryCreator creator;
	private CashPayment cashPayment;
	private CustomerDTO customer;
	private String date;

	/**
<<<<<<< HEAD
	 * Creates a new instance og <code>Sale</code>.
	 * 
	 * @param cashRegister
	 *            The {@link CashRegister} that the sale will use and update.
	 * @param creator
	 *            The {@link RegistryCreator} that is needed to get the important
	 *            registers.
=======
	 * Creates a <code>Sale</code> instance and records the date.
	 * 
	 * @param cashRegister
	 *            The {@link CashRegister} object that will be updated by the sale.
	 * @param creator
	 *            The {@link RegistryCreator} object that will be used by this sale.
>>>>>>> seminar4
	 */
	public Sale(CashRegister cashRegister, RegistryCreator creator) {
		this.cashRegister = cashRegister;
		this.creator = creator;
		recordDate();
	}

	/**
	 * The enterItem-operation. This will update the sale with the item that has the
	 * given item identifier, store it in the list as an {@link Item}-object or
	 * increase the quantity of the object by one if it is already stored and update
	 * the running total.
	 * 
	 * @param itemIdentifier
	 *            Unique for an {@link ItemDTO}.
<<<<<<< HEAD
	 * @return the {@link ItemDTO} that was found from the given item identifier.
	 * @throws InvalidIdentifierException
	 *             if no {@link ItemDTO} with the given item identifier could be
	 *             found.
=======
	 * @return a {@link ItemDTO} if one was found from the given item identifier.
	 *         Else it returns <code>null</code>.
	 * @throws InvalidIdentifierException
	 *             when no {@link ItemDTO} could be found with the given item
	 *             identifier.
>>>>>>> seminar4
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
<<<<<<< HEAD
	 * @return the {@link ItemDTO} with the given item identifier.
	 * @throws InvalidIdentifierException
	 *             if no {@link ItemDTO} with the given item identifier could be
	 *             found.
=======
	 * @return a {@link ItemDTO} if it has the given item identifier. Else it
	 *         returns <code>null</code>.
	 * @throws InvalidIdentifierException
	 *             when no {@link ItemDTO} could be found with the given item
	 *             identifier.
>>>>>>> seminar4
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
<<<<<<< HEAD
	 * @return the running total of this <code>Sale</code>.
=======
	 * @return the running total of this sale.
>>>>>>> seminar4
	 */
	public double getRunningTotal() {
		return this.costs.getRunningTotal();
	}

	/**
<<<<<<< HEAD
	 * @return the total cost of this <code>Sale</code>.
=======
	 * @return the total cost of this sale with taxes included.
>>>>>>> seminar4
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
	 * @return the updated total cost for the <code>Sale</code>.
	 * @throws InvalidIdentifierException
	 *             if no {@link CustomerDTO} with the given ID could be found.
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
	 * @return the change after the payment.
	 * @throws InvalidAmountException
	 *             whenever the paid amount is invalid to finish the sale.
	 */
	public double pay(double paidAmount) throws InvalidAmountException {
		if (this.cashPayment == null) {
			this.cashPayment = new CashPayment(paidAmount, this.costs, this.cashRegister);
		} else {
			this.cashPayment.updatePayment(paidAmount, this.costs);
		}
		double change = this.cashPayment.getChange();
		if (change < 0) {
<<<<<<< HEAD
			throw new InvalidAmountException("Need more payment. Current balance:", change); // return change;
=======
			throw new InvalidAmountException("Remaining payment: ", change);
>>>>>>> seminar4
		}
		this.cashPayment.updateCashRegister();
		return change;
	}

	/**
<<<<<<< HEAD
	 * @return the created {@link Receipt} for the <code>Sale</code>.
	 * @throws InvalidAmountException
	 *             In case the paid amount for this sale is less than the total
	 *             cost.
=======
	 * @return the created {@link Receipt} for the sale.
>>>>>>> seminar4
	 */
	public Receipt getReceipt() {
		Receipt receipt = new Receipt(customer, enteredItems, date, costs, cashPayment);
		return receipt;

	}

}
