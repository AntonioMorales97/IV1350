package se.kth.iv1350.processofsale.model;

/**
 * This exception is thrown whenever something fails in some registry.
 */
public class RegistryException extends RuntimeException {

	public RegistryException(String msg) {
		super(msg);
	}
}
