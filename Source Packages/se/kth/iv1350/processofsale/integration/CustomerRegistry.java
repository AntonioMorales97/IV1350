package se.kth.iv1350.processofsale.integration;

/**
 * This class purpose is to find customers in a database. But since there is no
 * database, the customers will probably be stored in this class.
 */

public class CustomerRegistry {
	private CustomerDTO[] customers = { new CustomerDTO("Antonio", "9706024453", 10) };

	CustomerRegistry() {
	}

	/**
	 * Searches for a customer with the given ID number.
	 * 
	 * @param id
	 *            ID number as a <code>String</code>.
	 * @return the customerDTO if found.
	 */
	public CustomerDTO findCustomer(String id) {
		for (int i = 0; i < customers.length; i++) {
			if (isSameId(customers[i], id)) {
				return customers[i];
			}
		}

		throw new IllegalArgumentException("Invalid ID");
	}

	private boolean isSameId(CustomerDTO customer, String id) {
		return customer.getId().equals(id);
	}
}
