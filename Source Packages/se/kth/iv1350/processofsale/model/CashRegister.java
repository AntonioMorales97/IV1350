package se.kth.iv1350.processofsale.model;

/**
 * A class that represents the cash register. Holds all the paid payments since
 * the beginning of the program.
 */
public class CashRegister {
	private double totalCashPaid = 0;
	private CashRegisterObserver observer;

	/**
	 * Creates an instance of <code>CashRegister</code>
	 * 
	 * @param observer
	 */
	public CashRegister(CashRegisterObserver observer) {
		this.observer = observer;
	}

	void addPayment(CashPayment payment) {
		this.totalCashPaid += payment.getTotalCost();
		this.observer.addPayment(payment);
	}

	/**
	 * @return the total cash in the cash register.
	 */
	public double getTotal() {
		return this.totalCashPaid;
	}
}
