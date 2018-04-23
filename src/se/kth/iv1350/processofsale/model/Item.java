package se.kth.iv1350.processofsale.model;

import se.kth.iv1350.processofsale.integration.ItemDTO;

public class Item {
	private ItemDTO itemDTO;
	private int quantity = 1;
	
	Item(ItemDTO itemDTO){
		this.itemDTO = itemDTO;
	}
	
	ItemDTO getItemDTO(){
		return this.itemDTO;
	}
	
	int getQuantity(){
		return this.quantity;
	}
	
}
