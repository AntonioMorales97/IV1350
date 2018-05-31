package se.kth.iv1350.processofsale.integration;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.processofsale.model.InvalidIdentifierException;

public class CustomerRegistryTest {

	private CustomerRegistry customerReg;
	private final String VALID_ID = "0123456789";
	private final String INVALID_ID = "-1";

	@Before
	public void setUp() {
		this.customerReg = CustomerRegistry.getCustomerRegistry();
	}

	@After
	public void tearDown() {
		this.customerReg = null;
	}

	@Test
	public void testFindCustomer() {
		try {
			CustomerDTO customerDTO = customerReg.findCustomer(VALID_ID);
			assertNotNull("ItemDTO is null", customerDTO);
		} catch (InvalidIdentifierException e) {
			e.printStackTrace();
			fail("Got exception.");
		}

	}

	@Test(expected = InvalidIdentifierException.class)
	public void testInvalidID() throws InvalidIdentifierException {
		customerReg.findCustomer(INVALID_ID);
	}

}