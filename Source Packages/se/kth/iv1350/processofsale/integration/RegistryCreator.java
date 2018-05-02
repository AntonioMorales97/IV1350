package se.kth.iv1350.processofsale.integration;

/**
 * A class responsible for creating all of the necessary registers.
 */

public class RegistryCreator {
	private ItemRegistry itemRegistry = new ItemRegistry();
	private CustomerRegistry customerRegistry = new CustomerRegistry();
	private AccountingRegistry accountingRegistry = new AccountingRegistry();
	private InventoryRegistry inventoryRegistry = new InventoryRegistry();

	/**
	 * Creates a <code>RegistryCreator</code>.
	 */
	public RegistryCreator() {
	}

	/**
	 * @return the created <code>ItemRegistry</code>.
	 */
	public ItemRegistry getItemReg() {
		return this.itemRegistry;
	}

	/**
	 * @return the <code>CustomerRegistry</code>.
	 */
	public CustomerRegistry getCustomerReg() {
		return this.customerRegistry;
	}
	
	/**
	 * @return the <code>AccountingRegistry</code>.
	 */
	public AccountingRegistry getAccountingReg() {
		return this.accountingRegistry;
	}
	
	/**
	 * @return the <code>InventoryRegistry</code>.
	 */
	public InventoryRegistry getInventoryReg() {
		return this.inventoryRegistry;
	}

}
