package se.kth.iv1350.processofsale.view;

import se.kth.iv1350.processofsale.controller.Controller;
import se.kth.iv1350.processofsale.model.CurrentInfo;
import se.kth.iv1350.processofsale.model.InvalidAmountException;
import se.kth.iv1350.processofsale.model.InvalidIdentifierException;
import se.kth.iv1350.processofsale.util.ErrorLogHandler;

/**
 * This class is a placeholder for the entire view for this application.
 */
public class View {
	private Controller controller;
	private ErrorHandler errorHandler = new ErrorHandler();
	private ErrorLogHandler errorLogHandler = new ErrorLogHandler();

	/**
	 * Creates an instance of View
	 * 
	 * @param controller
	 *            The controller that will run all operations.
	 */
	public View(Controller controller) {
		this.controller = controller;
	}

	/**
	 * Hardcoded user input that generates system operations.
	 */
	public void sampleExecution() {
		controller.startNewSale();
		try {
			CurrentInfo testItem = controller.enterItems(1, 10);
			System.out.println(testItem);
		} catch (InvalidIdentifierException exc) {
			sendException(exc.getMessage(), exc);
		}

		double totalCost = controller.itemRegistrationDone();
		System.out.println("Total cost: " + totalCost);
		try {
			totalCost = controller.discountRequest("0123456789");
		} catch (InvalidIdentifierException exc) {
			sendException(exc.getMessage(), exc);
		}
		System.out.println("New total cost: " + totalCost);
		try {
			controller.pay(100);

		} catch (InvalidAmountException e) {
			sendException(e.getMessage(), e);
		}
		try {
			controller.pay(5);
		} catch (InvalidAmountException e) {
			sendException(e.getMessage(), e);
		}
		controller.endSale();

	}

	private void sendException(String exceptionMsg, Exception exception) {
		errorHandler.showError(exceptionMsg);
		errorLogHandler.logError(exception);
	}
}
