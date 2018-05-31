package se.kth.iv1350.processofsale.integration;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.processofsale.model.InvalidIdentifierException;
import se.kth.iv1350.processofsale.model.RegistryException;

public class ItemRegistryTest {
	private ItemRegistry itemReg;
	private final int BANANA_ID = 1;
	private final int INVALID_ID = -1;
	private final int DATABASE_FAILURE = 1337;

	@Before
	public void setUp() {
		this.itemReg = ItemRegistry.getItemRegistry();
	}

	@After
	public void tearDown() {
		this.itemReg = null;
	}

	@Test
	public void testFindItem() {
		try {
			ItemDTO itemDTO = itemReg.findItem(BANANA_ID);
			assertNotNull("ItemDTO is null.", itemDTO);
		} catch (InvalidIdentifierException e) {
			e.printStackTrace();
			fail("Got exception.");
		}

	}

	@Test(expected = InvalidIdentifierException.class)
	public void testInvalidItemIdentifier() throws InvalidIdentifierException {
		itemReg.findItem(INVALID_ID);
	}

	@Test(expected = RegistryException.class)
	public void testDatabaseFailure() throws RegistryException {
		try {
			itemReg.findItem(DATABASE_FAILURE);
		} catch (InvalidIdentifierException e) {
			e.printStackTrace();
			fail("Got exception.");
		}
	}

}