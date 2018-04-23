package se.kth.iv1350.processofsale.model;

import se.kth.iv1350.processofsale.integration.ItemDTO;

/**
 * The Costs-class will contain and update all of the costs for the sale.
 */
class Costs {
	private double runningTotal = 0;
	private double totalCost = this.runningTotal; // will be changed

	Costs() {
	}

	void increaseRunningTotal(Item item) {
		ItemDTO itemDTO = item.getItemDTO();
		this.runningTotal += itemDTO.getPrice() * item.getQuantity();
	}

	double getRunningTotal() {
		return this.runningTotal;
	}
}
