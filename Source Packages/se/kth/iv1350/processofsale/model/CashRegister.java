package se.kth.iv1350.processofsale.model;

/**
 * A class that represents the cash register. Holds all the paid payments since
 * the beginning of the program.
 */
public class CashRegister {
	private double totalCashPaid = 0;

	public CashRegister() {
	}

	void addPayment(CashPayment payment) {
		this.totalCashPaid += payment.getTotalCost();
	}

	/**
	 * @return the total cash in the cash register.
	 */
	public double getTotal() {
		return this.totalCashPaid;
	}
}
