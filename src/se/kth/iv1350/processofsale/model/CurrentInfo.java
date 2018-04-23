package se.kth.iv1350.processofsale.model;

import se.kth.iv1350.processofsale.integration.ItemDTO;

public class CurrentInfo {
	private ItemDTO lastEnteredItem;
	private double runningTotal;
	
	public CurrentInfo(ItemDTO itemDTO, double runningTotal){
		this.lastEnteredItem = itemDTO;
		this.runningTotal = runningTotal;
	}
	
	public ItemDTO getItemDTO(){
		return this.lastEnteredItem;
	}
	
	public double getRunningTotal(){
		return this.runningTotal;
	}
}
