package se.kth.iv1350.processofsale.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CashPaymentTest {
	private CashRegister testCashRegister = new CashRegister();
	private CashPayment cashPayment;

	@Before
	public void setUp() {
		double paidAmount = 100;
		double totalCost = 110;
		this.cashPayment = new CashPayment(paidAmount, totalCost, this.testCashRegister);
	}

	@After
	public void tearDown() {
		this.cashPayment = null;
	}

	@Test
	public void testGetChange() {
		double expChange = -10;
		double actChange = this.cashPayment.getChange();
		assertEquals("Not the correct change.", expChange, actChange, 0.0f);

	}

	@Test
	public void testAddPaidAmount() {
		double firstAmount = this.cashPayment.getPaidAmount();
		this.cashPayment.addPaidAmount(100);
		double secondAmount = this.cashPayment.getPaidAmount();
		assertEquals("Not the correct paid amount.", firstAmount + 100, secondAmount, 0.0f);
		assertTrue("Not the correct change.", this.cashPayment.getChange() > 0);
	}

	@Test 
	public void testIllegalUpdate() {
		this.cashPayment.updateCashRegister();
		double expTotal = 0;
		double actTotal = this.testCashRegister.getTotal();
		assertEquals("Illegal update on the cash register. Not enough paid amount.", expTotal, actTotal, 0.0f);
	}

	@Test
	public void testUpdateCashRegister() {
		this.cashPayment.addPaidAmount(100);
		this.cashPayment.updateCashRegister();
		double expTotal = 110;
		double actTotal = this.testCashRegister.getTotal();
		assertEquals("Not the correct total in cash register.", expTotal, actTotal, 0.0f);
	}
}
