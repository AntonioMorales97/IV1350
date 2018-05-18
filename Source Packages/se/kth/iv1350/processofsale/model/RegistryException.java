package se.kth.iv1350.processofsale.model;

/**
 * This exception is thrown whenever something fails in some registry.
 */
public class RegistryException extends RuntimeException {

	/**
	 * Creates an instance of <code>RegistryException</code> with a given message.
	 * 
	 * @param msg
	 *            Information about the reason why the exception is thrown.
	 */
	public RegistryException(String msg) {
		super(msg);
	}
}
