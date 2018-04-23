package se.kth.iv1350.processofsale.model;

import se.kth.iv1350.processofsale.integration.RegistryCreator;
import se.kth.iv1350.processofsale.integration.ItemDTO;
import se.kth.iv1350.processofsale.integration.ItemRegistry;

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
	 * @param creator
	 *            The registry creator that is needed to get the important
	 *            registers.
	 */
	public Sale(CashRegister cashRegister, RegistryCreator creator) {
		this.cashRegister = cashRegister;
		this.creator = creator;
	}
	
	public ItemDTO enterItem(int itemIdentifier){
		ItemDTO foundItemDTO = findItemDTO(itemIdentifier);
		Item item = new Item (foundItemDTO); //this will be added in a list!
		this.costs.increaseRunningTotal(item);
		return foundItemDTO;
	}
	
	private ItemDTO findItemDTO(int itemIdentifier){
		ItemRegistry itemReg = creator.getItemReg();
		ItemDTO foundItemDTO = itemReg.findItem(itemIdentifier);
		return foundItemDTO;
	}
	
	public double getRunningTotal(){
		return this.costs.getRunningTotal();
	}
}
