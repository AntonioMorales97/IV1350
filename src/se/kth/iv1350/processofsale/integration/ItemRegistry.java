package se.kth.iv1350.processofsale.integration;

/**
 * This class purpose is to find and return items from a database. But since there is no
 * database, the items will probably be stored in this class.
 */

public class ItemRegistry {
	private ItemDTO[] itemDTOs = { new ItemDTO("banana", 10, 1), new ItemDTO("carrot", 15, 2) };

	ItemRegistry() {
	}

	public ItemDTO findItem(int itemIdentifier) {
		for (int i = 0; i < itemDTOs.length; i++) {
			if (isSameIdentifier(itemDTOs[i], itemIdentifier))
				return itemDTOs[i];
		}
		throw new IllegalArgumentException("Invalid item identifier");
	}

	private boolean isSameIdentifier(ItemDTO itemDTO, int identifier) {
		return identifier == itemDTO.getIdentifier();
	}

}
