package se.kth.iv1350.processofsale.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.processofsale.integration.CustomerDTO;
import se.kth.iv1350.processofsale.integration.CustomerRegistry;
import se.kth.iv1350.processofsale.integration.ItemDTO;
import se.kth.iv1350.processofsale.integration.ItemRegistry;
import se.kth.iv1350.processofsale.integration.RegistryCreator;

public class CostsTest {
	private int BANANA_ID = 1;
	private double BANANA_PRICE;
	private String CUSTOMER_ID = "0123456789";
	private Costs costs;
	private RegistryCreator creator = new RegistryCreator();
	private Item oneBanana;
	private Item twoBananas;

	@Before
	public void setUpTest() {
		this.costs = new Costs();
		ItemRegistry itemReg = creator.getItemReg();
		try {
			ItemDTO bananaDTO = itemReg.findItem(BANANA_ID);
			this.BANANA_PRICE = bananaDTO.getPrice();
			this.oneBanana = new Item(bananaDTO);
			this.twoBananas = new Item(bananaDTO, 2);
		} catch (InvalidIdentifierException e) {
			fail("Got exception.");
			e.printStackTrace();
		}
	}

	@After
	public void cleanUp() {
		this.costs = null;
	}

	@Test
	public void testIncreaseRunningTotalAndTotalCost() {
		this.costs.increaseRunningTotal(oneBanana);
		double runningTotal = this.costs.getRunningTotal();
		assertEquals("Not the correct running total.", BANANA_PRICE, runningTotal, 0.0f);
		this.costs.increaseRunningTotal(twoBananas);
		runningTotal = this.costs.getRunningTotal();
		assertEquals("Not the correct running total for 3 banans.", 3 * BANANA_PRICE, runningTotal, 0.0f);
		double totalCost = this.costs.getTotalCost();
		assertNotEquals("Running total is the same as total cost.", totalCost, runningTotal);
	}

	@Test
	public void testEnterDiscount() {
		CustomerRegistry customerReg = creator.getCustomerReg();
		try {
			CustomerDTO customer = customerReg.findCustomer(CUSTOMER_ID);
			this.costs.increaseRunningTotal(oneBanana);
			double totalCost = this.costs.getTotalCost();
			this.costs.enterDiscount(customer);
			double newTotalCost = this.costs.getTotalCost();
			boolean exp = newTotalCost < totalCost;
			assertTrue("Wrong expected total cost.", exp);

		} catch (InvalidIdentifierException e) {
			fail("Got exception.");
			e.printStackTrace();
		}

	}

}
