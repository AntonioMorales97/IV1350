package se.kth.iv1350.processofsale.integration;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RegistryCreatorTest {
	private RegistryCreator creator;

	@Before
	public void setUp() {
		this.creator = new RegistryCreator();
	}

	@After
	public void tearDown() {
		this.creator = null;
	}

	@Test
	public void testGetItemReg() {
		ItemRegistry expReg = this.creator.getItemReg();
		assertTrue("Failed to get ItemRegistry.", expReg instanceof ItemRegistry);
	}

	@Test
	public void testGetCustomerReg() {
		CustomerRegistry expReg = this.creator.getCustomerReg();
		assertTrue("Failed to get CustomerRegistry.", expReg instanceof CustomerRegistry);
	}

}
