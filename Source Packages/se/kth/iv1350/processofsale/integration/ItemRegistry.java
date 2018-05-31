package se.kth.iv1350.processofsale.integration;

import se.kth.iv1350.processofsale.model.InvalidIdentifierException;
import se.kth.iv1350.processofsale.model.RegistryException;

/**
 * This class purpose is to find and return items from a database. But since
 * there is no database, the items will probably be stored in this class.
 */

public class ItemRegistry {
	private static final ItemRegistry ITEM_REGISTRY = new ItemRegistry();
	private ItemDTO[] itemDTOs = { new ItemDTO("banana", 10, 1), new ItemDTO("carrot", 15, 2) };
	private final int DATABASE_FAILURE = 1337;

	private ItemRegistry() {
	}

	static ItemRegistry getItemRegistry() {
		return ItemRegistry.ITEM_REGISTRY;
	}

	/**
	 * Searches for the ItemDTO in the array with the same item identifier.
	 * 
	 * @param itemIdentifier
	 *            Unique identifier to an item.
	 * @return the {@link ItemDTO} that was found. Else it returns
	 *         <code>null</code>.
	 * @throws InvalidIdentifierException
	 *             when no {@link ItemDTO} could be found with the given item
	 *             identifier.
	 * @throws RegistryException
	 *             when failed to connect to the database. In this case this
	 *             situation is simulated.
	 */
	public ItemDTO findItem(int itemIdentifier) throws InvalidIdentifierException {
		for (int i = 0; i < itemDTOs.length; i++) {
			if (isSameIdentifier(itemDTOs[i], itemIdentifier))
				return itemDTOs[i];
		}

		if (itemIdentifier == DATABASE_FAILURE) {
			throw new RegistryException("Could not connect to database.");
		}
		throw new InvalidIdentifierException("The item with identifier: " + itemIdentifier + ", could not be found.");
	}

	private boolean isSameIdentifier(ItemDTO itemDTO, int identifier) {
		return identifier == itemDTO.getIdentifier();
	}

}
