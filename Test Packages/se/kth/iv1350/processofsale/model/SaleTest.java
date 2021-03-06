package se.kth.iv1350.processofsale.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.processofsale.integration.RegistryCreator;
import se.kth.iv1350.processofsale.view.TotalRevenueView;

public class SaleTest {
	private Sale sale;
	private int VALID_ITEM_ID = 1;
	private String VALID_CUSTOMER_ID = "0123456789";

	@Before
	public void setUp() {
		RegistryCreator creator = RegistryCreator.getCreator();
		CashRegister cashRegister = new CashRegister(new TotalRevenueView());
		this.sale = new Sale(cashRegister, creator);
		assertTrue("Failed to create instance of Sale.", this.sale instanceof Sale);
	}

	@After
	public void tearDown() {
		this.sale = null;
	}

	@Test
	public void testEnterItem() {
		try {
			this.sale.enterItem(VALID_ITEM_ID);
			double runningTotal = this.sale.getRunningTotal();
			boolean exp = runningTotal > 0;
			assertTrue("Failed to update running total.", exp);

		} catch (InvalidIdentifierException e) {
			e.printStackTrace();
			fail("Got exception.");
		}

	}
	
	@Test
	public void testEnterItems() {
		try {
			this.sale.enterItem(VALID_ITEM_ID);
			double firstRunningTotal = this.sale.getRunningTotal();
			this.sale.enterItems(VALID_ITEM_ID, 3);
			double newRunningTotal = this.sale.getRunningTotal();
			boolean exp = (firstRunningTotal * 4) == newRunningTotal;
			assertTrue("Wrong running total.", exp);

		} catch (InvalidIdentifierException e) {
			e.printStackTrace();
			fail("Got exception.");
		}

	}
	
	@Test
	public void testDiscountRequest() {
		try {
			this.sale.enterItem(VALID_ITEM_ID);
			double totalCost = this.sale.getTotal();
			double expTotalCost = this.sale.discountRequest(VALID_CUSTOMER_ID);
			boolean exp = expTotalCost < totalCost;
			assertTrue("Failed to update with discount. Same or more total cost.", exp);

		} catch (InvalidIdentifierException e) {
			e.printStackTrace();
			fail("Got exception.");
		}
	}

	@Test
	public void testPay() {
		try {
			this.sale.enterItem(VALID_ITEM_ID);
			double totalCost = this.sale.getTotal();
			double paidAmount = 15;
			double expChange = paidAmount-totalCost;
			double actChange = this.sale.pay(paidAmount);
			assertEquals("Wrong change.",expChange,actChange,0.0f);
		} catch(InvalidIdentifierException e) {
			e.printStackTrace();
			fail("Got exception.");
		} catch (InvalidAmountException e) {
			e.printStackTrace();
			fail("Got exception.");
		}
	}
	
	@Test (expected = InvalidAmountException.class)
	public void testInvalidPay() throws InvalidAmountException {
		try {
			this.sale.enterItem(VALID_ITEM_ID);
			this.sale.pay(0);
		} catch (InvalidIdentifierException e) {
			e.printStackTrace();
			fail("Got exception.");
		}
	}

}
