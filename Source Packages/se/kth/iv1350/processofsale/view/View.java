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
			CurrentInfo currentInfo = controller.enterItems(1, 10);
			currentInfo = controller.enterItem(2);
			System.out.println(currentInfo);
		} catch (InvalidIdentifierException exception) {
			sendException(exception.getMessage(), exception);
		}

		double totalCost = controller.itemRegistrationDone();
		System.out.println("Total cost: " + String.format("%.2f", totalCost));
		try {
			totalCost = controller.discountRequest("0123456789");
		} catch (InvalidIdentifierException exception) {
			sendException(exception.getMessage(), exception);
		}
		System.out.println("New total cost: " + String.format("%.2f", totalCost));
		try {
			controller.pay(100);

		} catch (InvalidAmountException exception) {
			sendException(exception.getMessage(), exception);
		}
		try {
			controller.pay(50);
		} catch (InvalidAmountException exception) {
			sendException(exception.getMessage(), exception);
		}
		controller.endSale();

	}

	private void sendException(String exceptionMsg, Exception exception) {
		errorHandler.showError(exceptionMsg);
		errorLogHandler.logError(exception);
	}
}
