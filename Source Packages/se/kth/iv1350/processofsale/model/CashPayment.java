package se.kth.iv1350.processofsale.model;

/**
 * Represents the payment in cash for sale and its change.
 */
class CashPayment {
	private double paidAmount;
	private double totalCost;
	private double change;
	private CashRegister cashRegister;

	CashPayment(double paidAmount, double totalCost, CashRegister cashRegister) {
		this.paidAmount = paidAmount;
		this.totalCost = totalCost;
		this.change = paidAmount - totalCost;
		this.cashRegister = cashRegister;
	}

	void addPaidAmount(double amount) {
		this.paidAmount += amount;
		this.change = paidAmount - totalCost;
	}

	double getPaidAmount() {
		return this.paidAmount;
	}

	double getTotalCost() {
		return this.totalCost;
	}

	double getChange() {
		return this.change;
	}

	void updateCashRegister() {
		if (this.change > 0)
			this.cashRegister.addPayment(this);
	}
}
