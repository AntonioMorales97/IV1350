package se.kth.iv1350.processofsale.util;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.kth.iv1350.processofsale.model.RegistryException;

public class ErrorFileLoggerTest {

	private ErrorFileLogger fileLogger;
	private String fileName = "processofsale-error-log.txt";

	@Before
	public void setUp() {
		try {
			this.fileLogger = new ErrorFileLogger();
		} catch (IOException exc) {
			exc.printStackTrace();
			fail("Unable to create a log handler.");
		}
	}

	@After
	public void tearDown() {
		this.fileLogger = null;
		File logFile = new File(fileName);
		logFile.delete();
	}

	@Test
	public void testFileLogException() throws FileNotFoundException {
		String expResultMsg = "Something failed in registry.";
		RuntimeException testExc = new RegistryException(expResultMsg);
		this.fileLogger.logException(testExc);;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String expDate = dateFormat.format(date).toString();
		String expStackTrace = "se.kth.iv1350.processofsale.util.ErrorFileLoggerTest.testFileLogException";
		boolean containsExpMsg = containsExpectedString(expResultMsg);
		assertTrue("Wrong exception message.", containsExpMsg);
		boolean containsExpDate = containsExpectedString(expDate);
		assertTrue("Wrong date format.", containsExpDate);
		boolean containsExpStackTrace = containsExpectedString(expStackTrace);
		assertTrue("Wrong stack trace.", containsExpStackTrace);
	}

	private boolean containsExpectedString(String expectedString) throws FileNotFoundException {
		Scanner in = new Scanner(new FileReader(fileName));
		String textLine = null;
		while (in.hasNext()) {
			textLine = in.nextLine();
			if (textLine.contains(expectedString)) {
				in.close();
				return true;
			}
		}
		in.close();
		return false;
	}

}
