package se.kth.iv1350.processofsale.integration;

/**
 * A class responsible for creating all of the necessary registers.
 */

public class RegistryCreator {
	private ItemRegistry itemRegistry = new ItemRegistry();

	/**
	 * Creates a <code>RegistryCreator</code>.
	 */
	public RegistryCreator() {
	}

	/**
	 * @return the created {@link ItemRegistry}.
	 */
	public ItemRegistry getItemReg() {
		return this.itemRegistry;
	}

}
