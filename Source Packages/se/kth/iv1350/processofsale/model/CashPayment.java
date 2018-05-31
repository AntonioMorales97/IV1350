package se.kth.iv1350.processofsale.model;

/**
 * Represents the payment in cash for sale and its change.
 */
public class CashPayment {
	private double paidAmount;
	private double totalCost;
	private double change;
	private CashRegister cashRegister;

	CashPayment(double paidAmount, Costs costs, CashRegister cashRegister) {
		this.paidAmount = paidAmount;
		this.totalCost = costs.getTotalCost();
		this.change = paidAmount - totalCost;
		this.cashRegister = cashRegister;
	}

	void updatePayment(double amount, Costs costs) {
		this.paidAmount += amount;
		this.totalCost = costs.getTotalCost();
		this.change = paidAmount - totalCost;
	}

	double getPaidAmount() {
		return this.paidAmount;
	}

	/**
	 * @return the total amount paid after a valid payment, which is equal to the
	 *         total cost for the sale.
	 */
	public double getTotalCost() {
		return this.totalCost;
	}

	double getChange() {
		return this.change;
	}

	void updateCashRegister() {
		if (this.change >= 0)
			this.cashRegister.addPayment(this);
	}
}
