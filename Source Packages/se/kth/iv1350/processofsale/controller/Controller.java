package se.kth.iv1350.processofsale.controller;

import se.kth.iv1350.processofsale.model.CashRegister;
import se.kth.iv1350.processofsale.model.CurrentInfo;
import se.kth.iv1350.processofsale.model.Sale;
import se.kth.iv1350.processofsale.integration.ItemDTO;
import se.kth.iv1350.processofsale.integration.RegistryCreator;


/**
 * The only controller class for this application. Every call from view passes
 * through here and then to the model.
 */
public class Controller {
	private CashRegister cashRegister = new CashRegister();
	private RegistryCreator creator = new RegistryCreator();
	private Sale sale;

	public Controller() {
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
	 */
	public CurrentInfo enterItem(int itemIdentifier) {
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
	 */
	public CurrentInfo enterItems(int itemIdentifier, int quantity) {
		ItemDTO foundItemDTO = sale.enterItems(itemIdentifier, quantity);
		return createCurrentInfo(foundItemDTO);
	}

	private CurrentInfo createCurrentInfo(ItemDTO itemDTO) {
		return new CurrentInfo(itemDTO, sale.getRunningTotal());
	}
	
	public Sale getSale() {
		return this.sale;
	}
}
