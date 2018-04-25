package se.kth.iv1350.processofsale.integration;

import se.kth.iv1350.processofsale.model.InvalidIdentifierException;

/**
 * This class purpose is to find and return items from a database. But since
 * there is no database, the items will probably be stored in this class.
 */

public class ItemRegistry {
	private ItemDTO[] itemDTOs = { new ItemDTO("banana", 10, 1), new ItemDTO("carrot", 15, 2) };

	ItemRegistry() {
	}

	/**
	 * Searches for the ItemDTO in the array with the same item identifier.
	 * 
	 * @param itemIdentifier
	 *            Unique identifier to an item.
	 * @return the ItemDTO that was found. Else it throws an exception.
	 * @throws InvalidIdentifierException
	 *             if no <code>ItemDTO</code> with the given item identifier could
	 *             be found.
	 */
	public ItemDTO findItem(int itemIdentifier) throws InvalidIdentifierException {
		for (int i = 0; i < itemDTOs.length; i++) {
			if (isSameIdentifier(itemDTOs[i], itemIdentifier))
				return itemDTOs[i];
		}
		throw new InvalidIdentifierException("Invalid item identifier.");
	}

	private boolean isSameIdentifier(ItemDTO itemDTO, int identifier) {
		return identifier == itemDTO.getIdentifier();
	}

}
