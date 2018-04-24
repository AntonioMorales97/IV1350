package se.kth.iv1350.processofsale.integration;

/**
 * A class that holds information about an item.
 */
public class ItemDTO {
	private final String name;
	private final double price;
	private final int identifier;

	ItemDTO(String name, double price, int identifier) {
		this.name = name;
		this.price = price;
		this.identifier = identifier;
	}

	@Override
	public String toString() {
		return "Name: " + this.name + "\n" + "Price: " + this.price;
	}

	/**
	 * @return The name of the <code>ItemDTO</code>.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return The price of the <code>ItemDTO</code>.
	 */
	public double getPrice() {
		return this.price;
	}

	/**
	 * @return The identifier of the <code>ItemDTO</code>.
	 */
	public int getIdentifier() {
		return this.identifier;
	}
}
