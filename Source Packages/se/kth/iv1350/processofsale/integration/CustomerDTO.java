package se.kth.iv1350.processofsale.integration;

/**
 * A class that holds information about a customer.
 */
public class CustomerDTO {
	private String name;
	private String id;
	private double discountPercent;

	CustomerDTO(String name, String id, double discountPercent) {
		this.name = name;
		this.id = id;
		this.discountPercent = discountPercent;
	}

	public String toString() {
		return "Name: " + this.name + "\n" + "Discount: " + this.discountPercent + " %";
	}

	String getId() {
		return this.id;
	}

	/**
	 * @return the discount percentage for the customer.
	 */
	public double getDiscountPercent() {
		return this.discountPercent;
	}

}
