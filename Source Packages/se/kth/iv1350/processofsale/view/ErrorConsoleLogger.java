package se.kth.iv1350.processofsale.view;

import se.kth.iv1350.processofsale.model.Logger;

/**
 * A class that is responsible to print out an <code>Exception</code> to the
 * console.
 */
public class ErrorConsoleLogger implements Logger {

	ErrorConsoleLogger() {
	}

	public void logException(Exception exc) {
		StringBuilder errorMsg = new StringBuilder();
		String frame = buildFrame();
		errorMsg.append("ERROR\n");
		errorMsg.append(frame + "\n");
		errorMsg.append(exc.getMessage() + "\n");
		errorMsg.append(frame);
		System.err.println(errorMsg);
	}

	private String buildFrame() {
		StringBuilder frame = new StringBuilder();
		for (int i = 0; i < 20; i++) {
			frame.append("-");
		}
		return frame.toString();
	}

}