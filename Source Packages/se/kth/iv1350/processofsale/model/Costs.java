package se.kth.iv1350.processofsale.model;

import se.kth.iv1350.processofsale.integration.CustomerDTO;
import se.kth.iv1350.processofsale.integration.ItemDTO;

/**
 * The Costs-class will contain and update all of the costs for the sale.
 */
class Costs {
	private TaxDTO tax = new TaxDTO();
	private double runningTotal = 0;
	private double totalCost = 0;
	private double valueAddedTax = 0;
	private double discountAmount = 0;

	Costs() {
	}

	void increaseRunningTotal(Item item) {
		ItemDTO itemDTO = item.getItemDTO();
		this.runningTotal += itemDTO.getPrice() * item.getQuantity();
		updateTotalCost();
		updateValueAddedTax();
	}

	private void updateTotalCost() {
		this.totalCost = this.runningTotal + (this.runningTotal * this.tax.getTax());
	}

	private void updateValueAddedTax() {
		this.valueAddedTax = this.runningTotal * this.tax.getTax();
	}

	double getRunningTotal() {
		return this.runningTotal;
	}

	double getTotalCost() {
		return this.totalCost;
	}

	double getTax() {
		return this.tax.getTax();
	}

	double getValueAddedTax() {
		return this.valueAddedTax;
	}

	double getDiscountAmount() {
		return this.discountAmount;
	}

	void enterDiscount(CustomerDTO customer) {
		double discountPercent = customer.getDiscountPercent();
		this.discountAmount = this.totalCost * (discountPercent / 100);
		this.totalCost -= this.discountAmount;
	}

}
