package se.kth.iv1350.processofsale.integration;

/**
 * A class responsible for creating all of the necessary registers.
 */

public class RegistryCreator {
	private ItemRegistry itemRegistry = new ItemRegistry();
	private CustomerRegistry customerRegistry = new CustomerRegistry();
	
	/**
	 * Creates a <code>RegistryCreator</code>.
	 */
	public RegistryCreator() {
	}
	
	/**
	 * @return the created <code>ItemRegistry</code>.
	 */
	public ItemRegistry getItemReg(){
		return this.itemRegistry;
	}

}
