package se.kth.iv1350.processofsale.model;

import se.kth.iv1350.processofsale.integration.ItemDTO;

/**
 * This class will represent an item and contain the ItemDTO of it and also the
 * quantity of the item.
 */
public class Item {
	private ItemDTO itemDTO;
	private int quantity = 1;

	Item(ItemDTO itemDTO) {
		this.itemDTO = itemDTO;
	}

	ItemDTO getItemDTO() {
		return this.itemDTO;
	}

	int getQuantity() {
		return this.quantity;
	}

	void increaseQuantity(int addition) {
		this.quantity += addition;
	}

	private int getItemDTOIdentifier() {
		return this.itemDTO.getIdentifier();
	}

	boolean equals(Item otherItem) {
		int currentIdentifier = getItemDTOIdentifier();
		return currentIdentifier == otherItem.getItemDTO().getIdentifier();
	}

}
