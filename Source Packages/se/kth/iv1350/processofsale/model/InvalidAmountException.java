package se.kth.iv1350.processofsale.model;

public class InvalidAmountException extends Exception {
	InvalidAmountException(String msg, double amount) {
		super(msg + String.format("%.2f", amount));
	}
}
