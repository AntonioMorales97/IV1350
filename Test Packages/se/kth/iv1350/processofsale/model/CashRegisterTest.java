package se.kth.iv1350.processofsale.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.processofsale.integration.ItemDTO;
import se.kth.iv1350.processofsale.integration.ItemRegistry;
import se.kth.iv1350.processofsale.integration.RegistryCreator;
import se.kth.iv1350.processofsale.view.TotalRevenueView;

public class CashRegisterTest {
	private ItemRegistry itemReg;
	private final int BANANA_ID = 1;
	
	@Before
	public void setUp() {
		RegistryCreator creator = RegistryCreator.getCreator();
		this.itemReg = creator.getItemReg();
	}

	@After
	public void tearDown() {
		this.itemReg = null;
	}


	@Test
	public void testAddPayment() throws InvalidIdentifierException {
		Costs costs = new Costs();
		ItemDTO bananaDTO = itemReg.findItem(BANANA_ID);
		Item bananaItem = new Item (bananaDTO);
		TaxDTO taxDTO = new TaxDTO();
		costs.increaseRunningTotal(bananaItem);
		CashRegister cashRegister = new CashRegister(new TotalRevenueView());
		double paidAmount = 100;
		double totalCost = 10 + 10*taxDTO.getTax();
		CashPayment cashPayment = new CashPayment(paidAmount, costs, cashRegister);
		cashPayment.updateCashRegister();
		double actTotal = cashRegister.getTotal();
		assertEquals("Wrong total in cash register.", totalCost, actTotal, 0.0f);
		CashPayment newCashPayment = new CashPayment(paidAmount, costs, cashRegister);
		newCashPayment.updateCashRegister();
		actTotal = cashRegister.getTotal();
		assertEquals("Wrong total in cash register after second update.", totalCost * 2, actTotal, 0.0f);
	}

}
