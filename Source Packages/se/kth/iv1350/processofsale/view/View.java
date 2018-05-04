package se.kth.iv1350.processofsale.view;

import se.kth.iv1350.processofsale.controller.Controller;
import se.kth.iv1350.processofsale.model.CurrentInfo;

/**
 * This class is a placeholder for the entire view for this application.
 */
public class View {
	private Controller controller;

	/**
	 * Creates an instance of <code>View</code>.
	 * 
	 * @param controller
	 *            The {@link Controller} that will call methods in the lower
	 *            layer(s) needed to perform a certain operation called from the
	 *            user.
	 */
	public View(Controller controller) {
		this.controller = controller;
	}

	/**
	 * Hardcoded user input that generates system operations.
	 */
	public void sampleExecution() {
		controller.startNewSale();
		CurrentInfo currentInfo = controller.enterItems(1, 10);
		System.out.println(currentInfo);
		currentInfo = controller.enterItem(2);
		System.out.println(currentInfo);
		double totalCost = controller.itemRegistrationDone();
		System.out.println("Total cost: " + String.format("%.2f", totalCost));
		controller.pay(150);
		controller.endSale();
	}

}
