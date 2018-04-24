package se.kth.iv1350.processofsale.view;

import se.kth.iv1350.processofsale.controller.*;
import se.kth.iv1350.processofsale.integration.ItemDTO;
import se.kth.iv1350.processofsale.model.CurrentInfo;
import se.kth.iv1350.processofsale.model.Item;

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
		CurrentInfo testItem = controller.enterItems(1,10);	
		System.out.println(testItem);
	}
}
