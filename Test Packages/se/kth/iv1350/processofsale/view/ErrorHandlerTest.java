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
		errorHandler.showError(errorMsg);
		CharSequence header = "ERROR";
		CharSequence frame = "-----";
		CharSequence expMsg = errorMsg;
		String result = errContent.toString();
		boolean containsHeader = result.contains(header);
		assertTrue("Wrong header.", containsHeader);
		boolean containsFrame = result.contains(frame);
		assertTrue("Does not contains frame.", containsFrame);
		boolean containsMsg = result.contains(expMsg);
		assertTrue("Does not contain expected error message.", containsMsg);
	}

}
