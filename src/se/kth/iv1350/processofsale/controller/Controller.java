package se.kth.iv1350.processofsale.controller;

import se.kth.iv1350.processofsale.model.CashRegister;
import se.kth.iv1350.processofsale.model.Sale;

/**
 * The only controller class for this application. Every call from view passes
 * through here and then to the model.
 */
public class Controller {
	private CashRegister cashRegister = new CashRegister();
	private Sale sale;

	public Controller() {
	}

	/**
	 * Creates a new Sale instance that will represent the current sale.
	 */
	public void startNewSale() {
		this.sale = new Sale(cashRegister);
	}
}
