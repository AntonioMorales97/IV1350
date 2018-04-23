package se.kth.iv1350.processofsale.model;

import se.kth.iv1350.processofsale.integration.ItemDTO;

/**
 * This class contains the last registered item and the running total after
 * that.
 */
public class CurrentInfo {
	private ItemDTO lastEnteredItem;
	private double runningTotal;

	/**
	 * Creates a <code>CurrentInfo</code>-object.
	 * 
	 * @param itemDTO
	 *            The last entered item that was added to the sale.
	 * @param runningTotal
	 *            The current running total.
	 */
	public CurrentInfo(ItemDTO itemDTO, double runningTotal) {
		this.lastEnteredItem = itemDTO;
		this.runningTotal = runningTotal;
	}

	/**
	 * @return the <code>ItemDTO</code> that is the last entered item.
	 */
	public ItemDTO getItemDTO() {
		return this.lastEnteredItem;
	}

	/**
	 * @return the updated running total.
	 */
	public double getRunningTotal() {
		return this.runningTotal;
	}
}
