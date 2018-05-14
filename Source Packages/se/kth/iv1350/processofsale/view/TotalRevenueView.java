package se.kth.iv1350.processofsale.view;

import se.kth.iv1350.processofsale.model.CashRegisterObserver;
import se.kth.iv1350.processofsale.model.CashPayment;

/**
 * This class notifies the user whenever the total in the cash register is
 * updated.
 */
public class TotalRevenueView implements CashRegisterObserver {
	private double total = 0;

	/**
	 * Updates the total and prints it to notify the user.
	 * 
	 * @param paidAmount
	 *            The paid amount by the customer.
	 */
	public void addPayment(CashPayment payment) {
		this.total += payment.getTotalCost();
		printCurrentTotalInCashRegister();
	}

	private void printCurrentTotalInCashRegister() {
		System.out.println("===========CashRegisterUpdate============");
		System.out.println("Current total in cash register: " + this.total);
		System.out.println("=========================================\n");
	}
}
