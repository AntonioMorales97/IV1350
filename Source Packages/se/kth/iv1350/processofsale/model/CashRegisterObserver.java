package se.kth.iv1350.processofsale.model;

/**
 * An interface so the total of the cash register can be seen.
 */
public interface CashRegisterObserver {

	void addPayment(CashPayment payment);

}
