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
		CharSequence totalCost = String.valueOf(sale.getTotal());
		boolean containsTotal = result.contains(totalCost);
		CharSequence runningTotal = String.valueOf(sale.getRunningTotal());
		boolean containsRunningTotal = result.contains(runningTotal);
		CharSequence date = String.valueOf(sale.getDate());
		boolean containsDate = result.contains(date);
		CharSequence itemName = item.getName();
		CharSequence itemPrice = String.valueOf(item.getPrice());
		boolean containsItemInfo = result.contains(itemName) && result.contains(itemPrice);
		boolean receiptHeader = result.contains("RECEIPT");
		boolean validPrint = containsTotal && containsRunningTotal && containsDate && containsItemInfo && receiptHeader;
		assertTrue("Wrong receipt format was printed out.", validPrint);

	}

}
