package se.kth.iv1350.processofsale.startup;

import java.io.IOException;

import se.kth.iv1350.processofsale.controller.*;
import se.kth.iv1350.processofsale.integration.Printer;
import se.kth.iv1350.processofsale.integration.RegistryCreator;
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
		try {
			RegistryCreator creator = RegistryCreator.getCreator();
			Printer printer = new Printer();
			Controller controller = new Controller(creator, printer);
			View view = new View(controller);
			view.sampleExecution();
		} catch (IOException exc) {
			System.err.println("Start up failed.");
			exc.printStackTrace();
		}

	}

}
