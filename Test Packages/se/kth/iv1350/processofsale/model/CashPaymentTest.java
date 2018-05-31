package se.kth.iv1350.processofsale.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.processofsale.integration.ItemDTO;
import se.kth.iv1350.processofsale.integration.ItemRegistry;
import se.kth.iv1350.processofsale.integration.RegistryCreator;
import se.kth.iv1350.processofsale.view.TotalRevenueView;

public class CashPaymentTest {
	private CashRegister testCashRegister = new CashRegister(new TotalRevenueView());
	private CashPayment cashPayment;
	private Costs costs;
	private final int BANANA_ID = 1;
	private TaxDTO tax = new TaxDTO();

	@Before
	public void setUp() throws InvalidIdentifierException {
		RegistryCreator creator = RegistryCreator.getCreator();
		ItemRegistry itemReg = creator.getItemReg();
		this.costs = new Costs();
		ItemDTO bananaDTO = itemReg.findItem(BANANA_ID);
		Item bananaItem = new Item(bananaDTO);
		this.costs.increaseRunningTotal(bananaItem);
		double paidAmount = 100;
		this.testCashRegister = new CashRegister(new TotalRevenueView());
		this.cashPayment = new CashPayment(paidAmount, this.costs, this.testCashRegister);
	}

	@After
	public void tearDown() {
		this.cashPayment = null;
		this.testCashRegister = null;
		this.costs = null;
	}

	@Test
	public void testGetChange() {
		double expChange = 100 - (10 + 10 * this.tax.getTax());
		double actChange = this.cashPayment.getChange();
		assertEquals("Not the correct change.", expChange, actChange, 0.0f);

	}

	@Test
	public void testAddPaidAmount() {
		Costs costs = new Costs();
		double firstAmount = this.cashPayment.getPaidAmount();
		this.cashPayment.updatePayment(100, costs);
		double secondAmount = this.cashPayment.getPaidAmount();
		assertEquals("Not the correct paid amount.", firstAmount + 100, secondAmount, 0.0f);
		assertTrue("Not the correct change.", this.cashPayment.getChange() > 0);
	}

	@Test
	public void testUpdateCashRegister() {
		this.cashPayment.updatePayment(100, this.costs);
		this.cashPayment.updateCashRegister();
		double expTotal = 10 + 10 * this.tax.getTax();
		double actTotal = this.testCashRegister.getTotal();
		assertEquals("Not the correct total in cash register.", expTotal, actTotal, 0.0f);
	}
}
