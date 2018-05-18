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
	private CashRegister cashRegister;
	private RegistryCreator creator;
	private Printer printer;
	private Sale sale;

	/**
	 * Creates an instance of <code>Controller</code>.
	 * 
	 * @param creator
	 *            The {@link RegistryCreator} that holds the needed registers
	 *            for the sale.
	 * @param printer
	 *            A {@link Printer} instance that is an interface to a printer
	 *            that does not exist.
	 */
	public Controller(RegistryCreator creator, Printer printer) {
		this.creator = creator;
		this.printer = printer;
	}

	/**
	 * Creates a new {@link Sale} instance that will represent the current
	 * ongoing sale.
	 */
	public void startNewSale() {
		this.sale = Sale.getSale(this.cashRegister, this.creator);
	}

	/**
	 * Updates the sale with the item that has the given item identifier and
	 * creates a {@link CurrentInfo} with the update.
	 * 
	 * @param itemIdentifier
	 *            An item identifier that is unique and identifies an item.
	 * @return {@link CurrentInfo} that was created with the updates.
	 * @throws InvalidIdentifierException
	 *             when no {@link ItemDTO} could be found with the given item
	 *             identifier.
	 */
	public CurrentInfo enterItem(int itemIdentifier) throws InvalidIdentifierException {
		ItemDTO foundItemDTO = sale.enterItem(itemIdentifier);
		return createCurrentInfo(foundItemDTO);
	}

	/**
	 * Updates the sale with the item with the given item identifier and also
	 * with the quantity of that item. Creates a {@link CurrentInfo} with the
	 * update.
	 * 
	 * @param itemIdentifier
	 *            Unique for an item and identifies an item.
	 * @param quantity
	 *            Number of items that has same identifier.
	 * @return the {@link CurrentInfo} that was created with the updates.
	 * @throws InvalidIdentifierException
	 *             when no {@link ItemDTO} could be found with the given item
	 *             identifier.
	 */
	public CurrentInfo enterItems(int itemIdentifier, int quantity) throws InvalidIdentifierException {
		ItemDTO foundItemDTO = sale.enterItems(itemIdentifier, quantity);
		return createCurrentInfo(foundItemDTO);
	}

	private CurrentInfo createCurrentInfo(ItemDTO itemDTO) {
		return new CurrentInfo(itemDTO, sale.getRunningTotal());
	}

	/**
	 * A method that returns the total cost and is used by the user to indicate
	 * that all the items has been registered.
	 * 
	 * @return the total cost of the current ongoing {@link Sale}.
	 */
	public double itemRegistrationDone() {
		double totalCost = this.sale.getTotal();
		return totalCost;
	}

	/**
	 * Pays the sale with the given paid amount and prints out a receipt if the
	 * paid amount is valid.
	 * 
	 * @param paidAmount
	 *            The amount given by the customer.
	 * @return the change.
	 */
	public double pay(double paidAmount) {
		double change = this.sale.pay(paidAmount);
		if (change < 0) {
			return change;
		}
		Receipt receipt = this.sale.getReceipt();
		this.printer.printReceipt(receipt);
		endSale();
		return change;
	}

	/**
	 * Adds a {@link CashRegisterObserver} that will observe the
	 * {@link CashRegister} for the sale.
	 * 
	 * @param observer
	 *            A class that implements the {@link CashRegisterObserver}
	 *            interface.
	 */
	public void addCashRegisterObserver(CashRegisterObserver observer) {
		this.cashRegister = new CashRegister(observer);
	}
	
	/**
	 * Ends the current {@link Sale}.
	 */
	public void endSale() {
		Sale.endSale();
		this.sale = null;
	}

}
