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
	 * @return the <code>RegistryCreator</code>'s only instance.
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

}
