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
	 * Creates a <code>RegistryCreator</code> instance.
	 */
	public RegistryCreator() {
	}

	/**
	 * @return the created {@link ItemRegistry}.
	 */
	public ItemRegistry getItemReg() {
		return this.itemRegistry;
	}

	/**
	 * @return the {@link CustomerRegistry}.
	 */
	public CustomerRegistry getCustomerReg() {
		return this.customerRegistry;
	}

	/**
	 * @return the {@link AccountingRegistry}.
	 */
	public AccountingRegistry getAccountingReg() {
		return this.accountingRegistry;
	}

	/**
	 * @return the {@link InventoryRegistry}.
	 */
	public InventoryRegistry getInventoryReg() {
		return this.inventoryRegistry;
	}

}
