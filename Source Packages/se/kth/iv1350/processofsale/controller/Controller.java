package se.kth.iv1350.processofsale.controller;

import se.kth.iv1350.processofsale.model.*;
import se.kth.iv1350.processofsale.integration.ItemDTO;
import se.kth.iv1350.processofsale.integration.Printer;
import se.kth.iv1350.processofsale.integration.RegistryCreator;

/**
 * This class is responsible for locating the action methods needed to execute
 * the action called from the view.
 */
public class Controller {
	private CashRegister cashRegister = new CashRegister();
	private RegistryCreator creator;
	private Printer printer;
	private Sale sale;

	/**
	 * Creates <code>Controller</code>.
	 */
	public Controller(RegistryCreator creator, Printer printer) {
		this.creator = creator;
		this.printer = printer;
	}

	/**
	 * Creates a new Sale instance that will represent the current sale.
	 */
	public void startNewSale() {
		this.sale = new Sale(cashRegister, creator);
	}

	/**
	 * Updates the sale with the item that has the given item identifier and creates
	 * a <code>CurrentInfo</code> with the update.
	 * 
	 * @param itemIdentifier
	 *            An item identifier that is unique and identifies an item
	 *            (ItemDTO).
	 * @return <code>CurrentInfo</code> that was created with the updates.
	 * @throws InvalidIdentifierException
	 *             when the given item identifier is invalid.
	 */
	public CurrentInfo enterItem(int itemIdentifier) throws InvalidIdentifierException {
		ItemDTO foundItemDTO = sale.enterItem(itemIdentifier);
		return createCurrentInfo(foundItemDTO);
	}

	/**
	 * Updates the sale with the item and quantity of the item. Creates a
	 * <code>CurrentInfo</code> with the update.
	 * 
	 * @param itemIdentifier
	 *            Unique for an item.
	 * @param quantity
	 *            Number of items that has same identifier.
	 * @return <code>CurrentInfo</code> that was created with the updates.
	 * @throws InvalidIdentifierException
	 *             when the given item identifier is invalid.
	 */
	public CurrentInfo enterItems(int itemIdentifier, int quantity) throws InvalidIdentifierException {
		ItemDTO foundItemDTO = sale.enterItems(itemIdentifier, quantity);
		return createCurrentInfo(foundItemDTO);
	}

	private CurrentInfo createCurrentInfo(ItemDTO itemDTO) {
		return new CurrentInfo(itemDTO, sale.getRunningTotal());
	}

	/**
	 * A method to use when all items for the sale are registered.
	 * 
	 * @return the total cost of the current <code>Sale</code>.
	 */
	public double itemRegistrationDone() {
		double totalCost = this.sale.getTotal();
		return totalCost;
	}

	/**
	 * Updates the total cost of the sale with a discount if a customer is found
	 * with the given ID number.
	 * 
	 * @return the updated total cost.
	 * @throws InvalidIdentifierException
	 *             if no customer with the given ID could be found.
	 */
	public double discountRequest(String id) throws InvalidIdentifierException {
		double newTotalCost = this.sale.discountRequest(id);
		return newTotalCost;
	}

	/**
	 * Pays the sale with the given paid amount and prints out a receipt.
	 * 
	 * @param paidAmount
	 *            The amount given by the customer.
	 * @return the change.
	 * @throws InvalidAmountException
	 *             if the paid amount is not enough to pay the sale.
	 */
	public double pay(double paidAmount) throws InvalidAmountException {
		double change = this.sale.pay(paidAmount);
		Receipt receipt = this.sale.getReceipt();
		this.printer.printReceipt(receipt);
		return change;
	}

	/**
	 * @return the total amount in the cash register since the start of the program.
	 */
	public double getTotalCashRegister() {
		return this.cashRegister.getTotal();
	}

	/**
	 * Ends the current sale.
	 */
	public void endSale() {
		this.sale = null;
	}
}
