package se.kth.iv1350.processofsale.integration;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.processofsale.model.CashRegister;
import se.kth.iv1350.processofsale.model.InvalidAmountException;
import se.kth.iv1350.processofsale.model.InvalidIdentifierException;
import se.kth.iv1350.processofsale.model.Receipt;
import se.kth.iv1350.processofsale.model.Sale;

public class PrinterTest {
	private int VALID_ITEM_ID = 1;
	private ByteArrayOutputStream outContent;
	private PrintStream originalSysOut;

	@Before
	public void setUp() {
		this.originalSysOut = System.out;
		this.outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(this.outContent));

	}

	@After
	public void tearDown() {
		this.outContent = null;
		System.setOut(this.originalSysOut);
	}

	@Test
	public void testPrintReceipt() {
		Printer printer = new Printer();
		CashRegister cashReg = new CashRegister();
		RegistryCreator creator = new RegistryCreator();
		Sale sale = new Sale(cashReg, creator);
		ItemDTO item = null;
		try {
			item = sale.enterItem(VALID_ITEM_ID);
			sale.pay(20);
			Receipt receipt = sale.getReceipt();
			printer.printReceipt(receipt);
		} catch (InvalidAmountException e) {
			e.printStackTrace();
			fail("Got exception.");
		} catch (InvalidIdentifierException e) {
			e.printStackTrace();
			fail("Got exception.");
		}

		String result = this.outContent.toString();
		CharSequence totalCost = String.format("%.2f", sale.getTotal());
		boolean containsTotal = result.contains(totalCost);
		assertTrue("Wrong total cost format printed out.", containsTotal);
		CharSequence runningTotal = String.valueOf(sale.getRunningTotal());
		boolean containsRunningTotal = result.contains(runningTotal);
		assertTrue("Wrong running total format printed out.", containsRunningTotal);
		CharSequence date = String.valueOf(sale.getDate());
		boolean containsDate = result.contains(date);
		assertTrue("Wrong date format printed out.", containsDate);
		CharSequence itemName = item.getName();
		CharSequence itemPrice = String.valueOf(item.getPrice());
		boolean containsItemInfo = result.contains(itemName) && result.contains(itemPrice);
		assertTrue("Wrong item info format printed out.", containsItemInfo);
		boolean receiptHeader = result.contains("RECEIPT");
		assertTrue("Wrong receipt header printed out.", receiptHeader);
	}

}
