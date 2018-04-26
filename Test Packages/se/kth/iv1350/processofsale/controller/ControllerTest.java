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
	private int VALID_ITEM_ID = 1;
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
		try {
			this.controller.startNewSale();
			this.controller.enterItem(VALID_ITEM_ID);
		} catch (NullPointerException e) {
			e.printStackTrace();
			fail("Failed to create a sale.");
		} catch (InvalidIdentifierException e) {
			e.printStackTrace();
			fail("Got exception.");
		}
		
	}

	@Test
	public void testEnterItem() {
		CurrentInfo currentInfo;
		try {
			currentInfo = this.controller.enterItem(VALID_ITEM_ID);
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
			CurrentInfo currentInfo = this.controller.enterItems(VALID_ITEM_ID, quantity);
			assertNotNull("CurrentInfo is null.", currentInfo);
		} catch (InvalidIdentifierException e) {
			fail("Got exception.");
			e.getStackTrace();
		}
	}
	
	@Test (expected = NullPointerException.class)
	public void testEndSale() {
		try {
		this.controller.endSale();
		this.controller.enterItem(VALID_ITEM_ID);
		} catch(InvalidIdentifierException e) {
			e.printStackTrace();
			fail("Got exception.");
		}
	}

}
