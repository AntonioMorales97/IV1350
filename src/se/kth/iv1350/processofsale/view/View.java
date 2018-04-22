package se.kth.iv1350.processofsale.view;

import se.kth.iv1350.processofsale.controller.*;

/**
 * This class is a placeholder for the entire view for this application.
 * 
 *
 */
public class View {
	private Controller controller;

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
	}
}
