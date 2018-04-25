package se.kth.iv1350.processofsale.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import se.kth.iv1350.processofsale.integration.ItemDTO;
import se.kth.iv1350.processofsale.integration.RegistryCreator;
import se.kth.iv1350.processofsale.model.CurrentInfo;
import se.kth.iv1350.processofsale.model.InvalidIdentifierException;
import se.kth.iv1350.processofsale.model.Sale;

public class ControllerTest {

	private Controller controller;
	private int banana = 1;
	private int quantity = 10;

	@Before
	public void setUp() {
		RegistryCreator creator = new RegistryCreator();
		this.controller = new Controller(creator);
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
		CurrentInfo currentInfo;
		try {
			currentInfo = this.controller.enterItem(banana);
			assertNotNull("CurrentInfo is null.");
			ItemDTO itemDTO = currentInfo.getItemDTO();
			assertNotNull("ItemDTO is null.", itemDTO);
			boolean expRunningTotal = currentInfo.getRunningTotal() > 0;
			assertTrue("Running total less than or equal to zero.", expRunningTotal);
		} catch (InvalidIdentifierException e) {
			fail("Got exception.");
			e.getStackTrace();
		}

	}

	@Test
	public void testEnterItems() {
		try {
			CurrentInfo currentInfo = this.controller.enterItems(banana, quantity);
			assertNotNull("CurrentInfo is null.", currentInfo);
		} catch (InvalidIdentifierException e) {
			fail("Got exception.");
			e.getStackTrace();
		}
	}

}
