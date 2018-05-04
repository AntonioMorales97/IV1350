package se.kth.iv1350.processofsale.integration;

import se.kth.iv1350.processofsale.model.Receipt;

/**
 * An interface to the real printer, used by this program.
 */
public class Printer {

	public Printer() {
	}

	/**
	 * Prints the given receipt. In this case no real printer is available, instead
	 * it prints to <code>System.out</code>.
	 * 
	 * @param receipt
	 *            The {@link Receipt} for the finished sale.
	 */
	public void printReceipt(Receipt receipt) {
		System.out.println(receipt);
	}

}
