package se.kth.iv1350.processofsale.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.processofsale.integration.ItemDTO;
import se.kth.iv1350.processofsale.integration.Printer;
import se.kth.iv1350.processofsale.integration.RegistryCreator;
import se.kth.iv1350.processofsale.model.CurrentInfo;
import se.kth.iv1350.processofsale.model.InvalidIdentifierException;

public class ControllerTest {

	private Controller controller;
	private int VALID_ITEM_ID = 1;
	private int QUANTITY = 10;

	@Before
	public void setUp() {
		RegistryCreator creator = RegistryCreator.getCreator();
		Printer printer = new Printer();
		this.controller = new Controller(creator, printer);
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
		try {
			CurrentInfo currentInfo = this.controller.enterItem(VALID_ITEM_ID);
			assertNotNull("CurrentInfo is null.");
			ItemDTO itemDTO = currentInfo.getItemDTO();
			assertNotNull("ItemDTO is null.", itemDTO);
			boolean expRunningTotal = currentInfo.getRunningTotal() > 0;
			assertTrue("Running total less than or equal to zero.", expRunningTotal);
		} catch (InvalidIdentifierException exc) {
			exc.printStackTrace();
			fail("Got exception.");
		}
	}

	@Test
	public void testEnterItems() {
		try {
			CurrentInfo currentInfo = this.controller.enterItems(VALID_ITEM_ID, QUANTITY);
			assertNotNull("CurrentInfo is null.", currentInfo);
		} catch (InvalidIdentifierException exc) {
			exc.printStackTrace();
			fail("Got exception.");
		}
	}

	@Test
	public void testItemRegistrationDone() {
		try {
			this.controller.enterItem(VALID_ITEM_ID);
			double totalCost = this.controller.itemRegistrationDone();
			boolean expTotalCost = totalCost > 0;
			assertTrue("Wrong total cost.", expTotalCost);
		} catch (InvalidIdentifierException exc) {
			exc.printStackTrace();
			fail("Got exception.");
		}
	}

	@Test
	public void testPay() {
		try {
			this.controller.enterItem(VALID_ITEM_ID);
			double totalCost = this.controller.itemRegistrationDone();
			double paidAmount = 20;
			double actChange = this.controller.pay(paidAmount);
			double expChange = paidAmount - totalCost;
			assertEquals("Wrong change.", expChange, actChange, 0.0f);
		} catch (InvalidIdentifierException exc) {
			exc.printStackTrace();
			fail("Got exception.");
		}
	}

	@Test(expected = NullPointerException.class)
	public void testEndSale() {
		try {
			this.controller.endSale();
			this.controller.enterItem(VALID_ITEM_ID);
		} catch (InvalidIdentifierException exc) {
			exc.printStackTrace();
			fail("Got exception.");
		}
	}

}
