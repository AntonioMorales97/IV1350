package se.kth.iv1350.processofsale.model;

class TaxDTO {
	private double valueAddedTax = 0.12;
	
	TaxDTO(){
	}
	
	double getTax(){
		return this.valueAddedTax;
	}
}
