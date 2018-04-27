package se.kth.iv1350.processofsale.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CashRegisterTest {

	@Test
	public void testAddPayment() {
		CashRegister cashRegister = new CashRegister();
		double paidAmount = 100;
		double totalCost = 70;
		CashPayment cashPayment = new CashPayment(paidAmount, totalCost, cashRegister);
		cashPayment.updateCashRegister();
		double actTotal = cashRegister.getTotal();
		assertEquals("Wrong total in cash register.", totalCost, actTotal, 0.0f);
		CashPayment newCashPayment = new CashPayment(paidAmount, totalCost, cashRegister);
		newCashPayment.updateCashRegister();
		actTotal = cashRegister.getTotal();
		assertEquals("Wrong total in cash register after second update.", totalCost * 2, actTotal, 0.0f);
	}

}
