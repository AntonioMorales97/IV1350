package se.kth.iv1350.processofsale.view;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ErrorHandlerTest {
	private ByteArrayOutputStream errContent;
	private PrintStream originalSysOut;

	@Before
	public void setUp() {
		this.originalSysOut = System.err;
		this.errContent = new ByteArrayOutputStream();
		System.setErr(new PrintStream(this.errContent));

	}

	@After
	public void tearDown() {
		this.errContent = null;
		System.setOut(this.originalSysOut);
	}

	@Test
	public void testErrorMessage() {
		ErrorHandler errorHandler = new ErrorHandler();
		String errorMsg = "invalid something";
		String errorFrame = "----------------------------------";
		errorHandler.showError(errorMsg);
		String expMsg = "ERROR\n" + errorFrame + "\n" + errorMsg + "\n" + errorFrame;
		String result = errContent.toString();
		assertTrue("Wrong printout.", result.contains(expMsg));

	}

}
