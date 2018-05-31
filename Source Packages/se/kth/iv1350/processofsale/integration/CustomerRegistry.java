package se.kth.iv1350.processofsale.integration;

import se.kth.iv1350.processofsale.model.InvalidIdentifierException;

/**
 * This class purpose is to find customers in a database. But since there is no
 * database, the customers will probably be stored in this class.
 */

public class CustomerRegistry {
	private static final CustomerRegistry CUSTOMER_REGISTRY = new CustomerRegistry();
	private CustomerDTO[] customers = { new CustomerDTO("Antonio", "0123456789", 10) };

	private CustomerRegistry() {
	}
	
	static CustomerRegistry getCustomerRegistry() {
		return CustomerRegistry.CUSTOMER_REGISTRY;
	}

	/**
	 * Searches for a {@link CustomerDTO} with the given ID number.
	 * 
	 * @param id
	 *            ID number as a <code>String</code>.
	 * @return the {@link CustomerDTO} if found.
	 * @throws InvalidIdentifierException
	 *             if no {@link CustomerDTO} with the given ID could be found.
	 */
	public CustomerDTO findCustomer(String id) throws InvalidIdentifierException {
		for (int i = 0; i < customers.length; i++) {
			if (isSameId(customers[i], id)) {
				return customers[i];
			}
		}
		throw new InvalidIdentifierException("The ID: "+id+", was invalid.");
	}

	private boolean isSameId(CustomerDTO customer, String id) {
		return customer.getId().equals(id);
	}
}