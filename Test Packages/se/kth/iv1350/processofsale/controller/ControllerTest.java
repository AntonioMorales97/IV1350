package se.kth.iv1350.processofsale.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import se.kth.iv1350.processofsale.integration.ItemDTO;
import se.kth.iv1350.processofsale.model.CurrentInfo;
import se.kth.iv1350.processofsale.model.Sale;

public class ControllerTest {

	private Controller controller;

	@Before
	public void setUp() {
		this.controller = new Controller();
		this.controller.startNewSale();

	}

	@After
	public void tearDown() {
		this.controller = null;
	}

	@Test
	public void testStartNewSale() {
		Sale expResult = this.controller.getSale();
		assertNotNull("Sale is null.", expResult);

	}

	@Test
	public void testEnterItem() {
		CurrentInfo currentInfo = this.controller.enterItem(1);
		assertNotNull("CurrentInfo is null.");
		ItemDTO itemDTO = currentInfo.getItemDTO();
		assertNotNull("ItemDTO is null.", itemDTO);
		boolean expRunningTotal = currentInfo.getRunningTotal() > 0;
		assertTrue("Running total less than or equal to zero.", expRunningTotal);
	}

	@Test
	public void testEnterItems() {
		CurrentInfo currentInfo = this.controller.enterItems(1, 10);
		assertNotNull("CurrentInfo is null.", currentInfo);
	}

}
