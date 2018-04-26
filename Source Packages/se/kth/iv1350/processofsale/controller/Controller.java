package se.kth.iv1350.processofsale.controller;

import se.kth.iv1350.processofsale.model.*;
import se.kth.iv1350.processofsale.integration.ItemDTO;
import se.kth.iv1350.processofsale.integration.RegistryCreator;

/**
 * The only controller class for this application. Every call from view passes
 * through here and then to the model.
 */
public class Controller {
	private CashRegister cashRegister = new CashRegister();
	private RegistryCreator creator;
	private Sale sale;

	/**
	 * Creates <code>Controller</code>.
	 */
	public Controller(RegistryCreator creator) {
		this.creator = creator;
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
	 * Ends the current sale.
	 */
	public void endSale() {
		this.sale = null;
	}
}
