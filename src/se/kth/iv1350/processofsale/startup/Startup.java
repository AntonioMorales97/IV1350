package se.kth.iv1350.processofsale.startup;

import se.kth.iv1350.processofsale.controller.*;
import se.kth.iv1350.processofsale.view.*;

/**
 * Contains the <code>main</code> method and performs the startup for the
 * application.
 * 
 * @author Antonio
 */
public class Startup {
	/**
	 * Starts the application
	 * 
	 * @param args
	 *            The application does not take command line parameters.
	 */

	public static void main(String[] args) {
		Controller controller = new Controller();
		View view = new View(controller);
		view.sampleExecution();
	}

}
