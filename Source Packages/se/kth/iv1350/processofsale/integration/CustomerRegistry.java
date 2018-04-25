package se.kth.iv1350.processofsale.integration;

import se.kth.iv1350.processofsale.model.InvalidIdentifierException;

/**
 * This class purpose is to find customers in a database. But since there is no
 * database, the customers will probably be stored in this class.
 */

public class CustomerRegistry {
	private CustomerDTO[] customers = { new CustomerDTO("Antonio", "0123456789", 10) };

	CustomerRegistry() {
	}

	/**
	 * Searches for a customer with the given ID number.
	 * 
	 * @param id
	 *            ID number as a <code>String</code>.
	 * @return the customerDTO if found.
	 * @throws InvalidIdentifierException
	 *             if no <code>CustomerDTO</code> with the given ID could be found.
	 */
	public CustomerDTO findCustomer(String id) throws InvalidIdentifierException {
		for (int i = 0; i < customers.length; i++) {
			if (isSameId(customers[i], id)) {
				return customers[i];
			}
		}

		throw new InvalidIdentifierException("Invalid ID");
	}

	private boolean isSameId(CustomerDTO customer, String id) {
		return customer.getId().equals(id);
	}
}
