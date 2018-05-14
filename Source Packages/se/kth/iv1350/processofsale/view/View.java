package se.kth.iv1350.processofsale.view;

import java.io.IOException;

import se.kth.iv1350.processofsale.controller.Controller;
import se.kth.iv1350.processofsale.model.CurrentInfo;
import se.kth.iv1350.processofsale.model.InvalidIdentifierException;
import se.kth.iv1350.processofsale.model.Logger;
import se.kth.iv1350.processofsale.model.RegistryException;
import se.kth.iv1350.processofsale.util.ErrorFileLogger;

/**
 * This class is a placeholder for the entire view for this application.
 */
public class View {
	private Controller controller;
	private Logger logger;

	/**
	 * Creates an instance of <code>View</code>.
	 * 
	 * @param controller
	 *            The {@link Controller} that will call methods in the lower
	 *            layer(s) needed to perform a certain operation called from the
	 *            user.
	 */
	public View(Controller controller) throws IOException {
		this.controller = controller;
		this.controller.addCashRegisterObserver(new TotalRevenueView());
	}

	/**
	 * Hardcoded user input that generates system operations.
	 */
	public void sampleExecution() {
		controller.startNewSale();
		
		try {
			CurrentInfo currentInfo = controller.enterItems(1, 10);
			System.out.println(currentInfo);
		} catch (InvalidIdentifierException exc) {
			sendException(exc);
		}
		
		try {
			CurrentInfo currentInfo = controller.enterItem(2);
			System.out.println(currentInfo);
		} catch (InvalidIdentifierException exc) {
			sendException(exc);
		}
		
		try {
			CurrentInfo currentInfo = controller.enterItem(-1);
			System.out.println(currentInfo);
		} catch (InvalidIdentifierException exc) {
			sendException(exc);
		}


		databaseFailureExecution();

		double totalCost = controller.itemRegistrationDone();
		System.out.println("Total cost: " + String.format("%.2f", totalCost) + "\n");
		controller.pay(150);
		controller.endSale();
	}

	private void sendException(Exception exc) {
		try {
			setLoggerType(exc);
		} catch (IOException e) {
			throw new RuntimeException("Failed to open log file.");
		}
		this.logger.logException(exc);
	}

	private void setLoggerType(Exception exc) throws IOException {
		if (exc instanceof Exception) {
			this.logger = new ErrorConsoleLogger();
		} else if (exc instanceof RuntimeException) {
			this.logger = new ErrorFileLogger();
		} else {
			throw new RuntimeException("Something went wrong when setting logger type.");
		}
	}

	private void databaseFailureExecution() {
		try {
			int databaseFailureItemIdentifier = 1337;
			CurrentInfo currentInfo = controller.enterItem(databaseFailureItemIdentifier);
			System.out.println(currentInfo);
		} catch (RegistryException exc) {
			sendException(exc);
			throw exc; // or log it in the exception class or just continue?
		} catch (InvalidIdentifierException exc) {
			sendException(exc);
		}
	}
}
