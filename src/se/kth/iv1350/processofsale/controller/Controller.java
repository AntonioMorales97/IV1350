package se.kth.iv1350.processofsale.controller;

import se.kth.iv1350.processofsale.model.CashRegister;
import se.kth.iv1350.processofsale.model.CurrentInfo;
import se.kth.iv1350.processofsale.model.Sale;
import se.kth.iv1350.processofsale.integration.ItemDTO;
import se.kth.iv1350.processofsale.integration.RegistryCreator;
import se.kth.iv1350.processofsale.model.Item;

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
	
	public CurrentInfo enterItem (int itemIdentifier){
		ItemDTO foundItemDTO = sale.enterItem(itemIdentifier);
		double runningTotal = sale.getRunningTotal();
		CurrentInfo currentInfo = new CurrentInfo(foundItemDTO,runningTotal); //this should have runningTotal as well.
		return currentInfo;
	}
}
