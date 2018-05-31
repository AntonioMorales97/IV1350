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
	 *            The {@link RegistryCreator} that holds the needed registers for
	 *            the sale.
	 * @param printer
	 *            A {@link Printer} instance that is an interface to a printer that
	 *            does not exist.
	 */
	public Controller(RegistryCreator creator, Printer printer) {
		this.creator = creator;
		this.printer = printer;
	}

	/**
	 * Creates a new {@link Sale} instance that will represent the current ongoing
	 * sale.
	 */
	public void startNewSale() {
		if (this.sale == null) {
			this.sale = new Sale(this.cashRegister, this.creator);
		}
	}

	/**
<<<<<<< HEAD
	 * Updates the {@link Sale} with the item that has the given item identifier and
	 * creates a {@link CurrentInfo} with the update.
=======
	 * Updates the sale with the item that has the given item identifier and creates
	 * a {@link CurrentInfo} with the update.
>>>>>>> seminar4
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
<<<<<<< HEAD
	 * Updates the {@link Sale} with the item and the quantity of the item. Creates
	 * a {@link CurrentInfo} with the update.
=======
	 * Updates the sale with the item with the given item identifier and also with
	 * the quantity of that item. Creates a {@link CurrentInfo} with the update.
>>>>>>> seminar4
	 * 
	 * @param itemIdentifier
	 *            Unique for an item and identifies an item.
	 * @param quantity
	 *            Number of items that has same identifier.
<<<<<<< HEAD
	 * @return {@link CurrentInfo} that was created with the updates.
=======
	 * @return the {@link CurrentInfo} that was created with the updates.
>>>>>>> seminar4
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
<<<<<<< HEAD
	 * A method to use when all items are registered.
	 * 
	 * @return the total cost of the current {@link Sale}.
=======
	 * A method that returns the total cost and is used by the user to indicate that
	 * all the items has been registered.
	 * 
	 * @return the total cost of the current ongoing {@link Sale}.
>>>>>>> seminar4
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
<<<<<<< HEAD
	 * Pays the sale with the given paid amount and prints out a {@link Receipt}.
=======
	 * Pays the sale with the given paid amount and prints out a receipt if the paid
	 * amount is valid.
>>>>>>> seminar4
	 * 
	 * @param paidAmount
	 *            The amount given by the customer.
	 * @return the change.
	 * @throws InvalidAmountException
	 *             whenever the paid amount is invalid to finish a sale.
	 */
	public double pay(double paidAmount) throws InvalidAmountException {
		double change = this.sale.pay(paidAmount);
		Receipt receipt = this.sale.getReceipt();
		this.printer.printReceipt(receipt);
		endSale();
		return change;
	}

	/**
<<<<<<< HEAD
	 * @return the total amount in the {@link CashRegister} since the start of the
	 *         program.
=======
	 * Adds a {@link CashRegisterObserver} that will observe the
	 * {@link CashRegister} for the sale.
	 * 
	 * @param observer
	 *            A class that implements the {@link CashRegisterObserver}
	 *            interface.
>>>>>>> seminar4
	 */
	public void addCashRegisterWithObserver(CashRegisterObserver observer) {
		this.cashRegister = new CashRegister(observer);
	}

	/**
<<<<<<< HEAD
	 * Ends the current ongoing {@link Sale}.
=======
	 * Ends the current {@link Sale}.
>>>>>>> seminar4
	 */
	public void endSale() {
		this.sale = null;
	}

}
