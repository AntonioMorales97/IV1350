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
		
			ItemDTO item = this.testSale.enterItem(VALID_ITEM_ID);
			this.testSale.pay(20);
			Receipt receipt = this.testSale.getReceipt();
			String receiptString = receipt.toString();
			CharSequence totalCost = String.format("%.2f", this.testSale.getTotal());
			boolean containsTotal = receiptString.contains(totalCost);
			assertTrue("Wrong total cost format.", containsTotal);
			CharSequence runningTotal = String.valueOf(this.testSale.getRunningTotal());
			boolean containsRunningTotal = receiptString.contains(runningTotal);
			assertTrue("Wrong running total format.", containsRunningTotal);
			String valueAddedTax = "VAT: ";
			boolean containsVAT = receiptString.contains(valueAddedTax);
			assertTrue("Wrong VAT format.", containsVAT);
			CharSequence date = String.valueOf(this.testSale.getDate());
			boolean containsDate = receiptString.contains(date);
			assertTrue("Wrong date format.", containsDate);
			CharSequence itemName = item.getName();
			CharSequence itemPrice = String.valueOf(item.getPrice());
			boolean containsItemInfo = receiptString.contains(itemName) && receiptString.contains(itemPrice);
			assertTrue("Wrong item info format.", containsItemInfo);
			boolean receiptHeader = receiptString.contains("RECEIPT");
			assertTrue("Wrong receipt header format.", receiptHeader);
		
	}

}
