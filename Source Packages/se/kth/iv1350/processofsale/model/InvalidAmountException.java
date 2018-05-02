package se.kth.iv1350.processofsale.model;

/**
 * This exception is thrown whenever the amount is still below the total cost that needs to be paid. 
 */
public class InvalidAmountException extends Exception {
	InvalidAmountException(String msg, double amount) {
		super(msg + String.format("%.2f", amount));
	}
}
