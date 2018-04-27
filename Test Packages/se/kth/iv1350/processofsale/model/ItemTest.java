package se.kth.iv1350.processofsale.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.processofsale.integration.ItemDTO;
import se.kth.iv1350.processofsale.integration.ItemRegistry;
import se.kth.iv1350.processofsale.integration.RegistryCreator;

public class ItemTest {
	private int VALID_ITEM_ID = 1;
	private RegistryCreator creator;
	private ItemRegistry itemReg;

	@Before
	public void setUp() {
		this.creator = new RegistryCreator();
		this.itemReg = creator.getItemReg();
	}

	@After
	public void tearDown() {
		this.creator = null;
		this.itemReg = null;
	}

	@Test
	public void testQuantities() {
		try {
			ItemDTO itemDTO = itemReg.findItem(VALID_ITEM_ID);
			Item item = new Item(itemDTO);
			int expQuantity = 1;
			int actQuantity = item.getQuantity();
			assertEquals("Wrong quantity of items.", expQuantity, actQuantity);
			expQuantity = 5;
			item.increaseQuantity(4);
			actQuantity = item.getQuantity();
			assertEquals("Failed to increase quantity.", expQuantity, actQuantity);
		} catch (InvalidIdentifierException e) {
			e.printStackTrace();
			fail("Got excpetion.");
		}
	}

	@Test
	public void testEquals() {
		try {
			ItemDTO itemDTO = itemReg.findItem(VALID_ITEM_ID);
			Item firstItemInstance = new Item(itemDTO);
			Item secondItemInstance = new Item(itemDTO,10);
			boolean expTrue = firstItemInstance.equals(secondItemInstance);
			assertTrue("Objects of the same type are not seen as equal.",expTrue);
		} catch (InvalidIdentifierException e) {
			e.printStackTrace();
			fail("Got excpeption.");
		}
		
	}

}