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
<<<<<<< HEAD
	 * Creates a new instance of <code>CurrentInfo</code>.
=======
	 * Creates a new instance.
>>>>>>> seminar4
	 * 
	 * @param itemDTO
	 *            The last {@link ItemDTO} that was added to the sale.
	 * @param runningTotal
	 *            The current running total.
	 */
	public CurrentInfo(ItemDTO itemDTO, double runningTotal) {
		this.lastEnteredItem = itemDTO;
		this.runningTotal = runningTotal;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Entered Item\n");
		sb.append(this.lastEnteredItem + "\n");
		sb.append("Running total: " + this.runningTotal+"\n");
		return sb.toString();

	}

	/**
	 * @return the {@link ItemDTO} which is the last entered to the sale.
	 */
	public ItemDTO getItemDTO() {
		return this.lastEnteredItem;
	}

	/**
	 * @return the current running total.
	 */
	public double getRunningTotal() {
		return this.runningTotal;
	}
}
