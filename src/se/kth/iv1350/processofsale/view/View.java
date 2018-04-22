package se.kth.iv1350.processofsale.view;

import se.kth.iv1350.processofsale.controller.*;

public class View {
	private Controller controller;
	
	
	/**
	 * Creates an instance of View
	 * 
	 * @param controller The controller that will
	 * 					 run all operations.
	 */
    public View (Controller controller) {
    	this.controller = controller;
    }
    
    /**
     * Hardcoded user input that generates 
     * system operations.
     */
    public void sampleExecution() {
    	
    }
}
