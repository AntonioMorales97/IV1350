package se.kth.iv1350.processofsale.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.processofsale.integration.ItemDTO;
import se.kth.iv1350.processofsale.integration.RegistryCreator;

public class ReceiptTest {
	private Sale testSale;
	private int VALID_ITEM_ID = 1;

	@Before
	public void setUp() {
		CashRegister cashRegister = new CashRegister();
		RegistryCreator creator = new RegistryCreator();
		this.testSale = new Sale(cashRegister, creator);
	}

	@After
	public void tearDown() {
		this.testSale = null;
	}

	@Test
	public void testReceipt() {
		try {
			ItemDTO item = this.testSale.enterItem(VALID_ITEM_ID);
			this.testSale.pay(20);
			Receipt receipt = this.testSale.getReceipt();
			String receiptString = receipt.toString();
			CharSequence totalCost = String.valueOf(this.testSale.getTotal());
			boolean containsTotal = receiptString.contains(totalCost);
			CharSequence runningTotal = String.valueOf(this.testSale.getRunningTotal());
			boolean containsRunningTotal = receiptString.contains(runningTotal);
			CharSequence date = String.valueOf(this.testSale.getDate());
			boolean containsDate = receiptString.contains(date);
			CharSequence itemName = item.getName();
			CharSequence itemPrice = String.valueOf(item.getPrice());
			boolean containsItemInfo = receiptString.contains(itemName) && receiptString.contains(itemPrice);
			boolean receiptHeader = receiptString.contains("RECEIPT");
			boolean validReceipt = containsTotal && containsRunningTotal && containsDate && containsItemInfo && receiptHeader;
			assertTrue("Wrong receipt format.", validReceipt);
		} catch (InvalidIdentifierException e) {
			e.printStackTrace();
			fail("Got exception.");
		} catch (InvalidAmountException e) {
			e.printStackTrace();
			fail("Got exception");
		}
	}

}
