package se.kth.iv1350.processofsale.model;

import se.kth.iv1350.processofsale.integration.RegistryCreator;

/**
 * This class will contain everything a sale should contain and methods to
 * perform a sale.
 */

public class Sale {
	private CashRegister cashRegister;
	private Costs costs = new Costs();
	private RegistryCreator creator;

	/**
	 * Creates a new instance.
	 * 
	 * @param cashRegister
	 *            The cash register that the sale will use and update.
	 */
	public Sale(CashRegister cashRegister, RegistryCreator creator) {
		this.cashRegister = cashRegister;
		this.creator = creator; 
	}
}
