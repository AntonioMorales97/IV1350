package se.kth.iv1350.processofsale.model;

import se.kth.iv1350.processofsale.integration.RegistryCreator;

import java.util.*;

import se.kth.iv1350.processofsale.integration.ItemDTO;
import se.kth.iv1350.processofsale.integration.ItemRegistry;

/**
 * This class will contain everything a sale should contain and methods to
 * perform a sale.
 */

public class Sale {
	private List<Item> enteredItems = new ArrayList<>();
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

	/**
	 * The enterItem-operation. This will update the sale with the item that has the
	 * given item identifier, store it in the list as an <code>Item</code>-object or
	 * increase the quantity of the object by one if it is already stored and update
	 * the running total.
	 * 
	 * @param itemIdentifier
	 *            Unique for an <code>ItemDTO</code>.
	 * @return the <code>ItemDTO</code> that was found from the given item
	 *         identifier.
	 */
	public ItemDTO enterItem(int itemIdentifier) {
		ItemDTO foundItemDTO = findItemDTO(itemIdentifier);
		Item newItem = new Item(foundItemDTO); // this will be added in a list!
		this.costs.increaseRunningTotal(newItem);
		updateItems(newItem);
		return foundItemDTO;
	}

	private ItemDTO findItemDTO(int itemIdentifier) {
		ItemRegistry itemReg = creator.getItemReg();
		ItemDTO foundItemDTO = itemReg.findItem(itemIdentifier);
		return foundItemDTO;
	}

	private void updateItems(Item newItem) {
		if (!isAlreadyEntered(newItem)) {
			this.enteredItems.add(newItem);
		} else {
			for (int i = 0; i < this.enteredItems.size(); i++) {
				if (this.enteredItems.get(i).equals(newItem)) {
					this.enteredItems.get(i).increaseQuantity(newItem.getQuantity());
				}
			}
		}
	}

	private boolean isAlreadyEntered(Item newItem) {
		return this.enteredItems.contains(newItem);
	}

	/**
	 * @return the running total of this sale which is stored in <code>Costs</code>.
	 */
	public double getRunningTotal() {
		return this.costs.getRunningTotal();
	}
}
