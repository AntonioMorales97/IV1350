package se.kth.iv1350.processofsale.model;

/**
 * This exception is thrown whenever an invalid item identifier or customer identifier is detected.
 */
public class InvalidIdentifierException extends Exception {
	
	public InvalidIdentifierException(String msg) {
		super(msg);
	}
}
