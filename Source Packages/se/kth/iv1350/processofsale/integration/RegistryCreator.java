package se.kth.iv1350.processofsale.integration;

/**
 * A class responsible for creating all of the necessary registers.
 */

public class RegistryCreator {
	private static final RegistryCreator creator = new RegistryCreator();
	private ItemRegistry itemRegistry = ItemRegistry.getItemRegistry();
	private CustomerRegistry customerRegistry = CustomerRegistry.getCustomerRegistry();

	private RegistryCreator() {
	}

	/**
<<<<<<< HEAD
	 * Creates a <code>RegistryCreator</code> instance.
=======
	 * @return the <code>RegistryCreator</code>'s only instance.
>>>>>>> seminar4
	 */
	public static RegistryCreator getCreator() {
		return RegistryCreator.creator;
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
<<<<<<< HEAD

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
=======
>>>>>>> seminar4

}
