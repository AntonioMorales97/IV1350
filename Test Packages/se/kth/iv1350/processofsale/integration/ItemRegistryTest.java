package se.kth.iv1350.processofsale.integration;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ItemRegistryTest {
	private ItemRegistry itemReg;
	private final int BANANA_ID = 1;

	@Before
	public void setUp() {
		this.itemReg = new ItemRegistry();
	}

	@After
	public void tearDown() {
		this.itemReg = null;
	}

	@Test
	public void testFindItem() {
		ItemDTO itemDTO = itemReg.findItem(BANANA_ID);
		assertNotNull("ItemDTO is null.", itemDTO);
	}
}
