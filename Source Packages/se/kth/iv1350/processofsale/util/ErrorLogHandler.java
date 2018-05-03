package se.kth.iv1350.processofsale.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class takes care of logging all of the exceptions that are given to a
 * file.
 */
public class ErrorLogHandler {
	private static final String LOG_FILE_NAME = "processofsale-error-log.txt";
	private PrintWriter writer;

	/**
	 * Creates a <code>PrintWriter</code> to a file, which is the file that the
	 * exceptions will be logged.
	 * 
	 * @throws IOException
	 *             when failed to open the log file.
	 */
	public ErrorLogHandler() throws IOException {
		this.writer = new PrintWriter(new FileWriter(LOG_FILE_NAME, true));
	}

	/**
	 * Logs the given exception in a file.
	 * 
	 * @param exc
	 *            The exception that occurred.
	 */
	public void logError(Exception exc) {
		StringBuilder errorRapport = new StringBuilder();
		errorRapport.append("Exception " + getDate() + "\r\n");
		errorRapport.append(getFrame() + "\r\n");
		String errorMsg = exc.getMessage();
		errorRapport.append(errorMsg + "\r\n");
		errorRapport.append(getFrame());
		writer.println(errorRapport);
		writer.println("Exception stack trace: ");
		exc.printStackTrace(writer);
		writer.println();
		writer.close();
	}

	private String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date).toString();
	}

	private String getFrame() {
		StringBuilder frame = new StringBuilder();
		for (int i = 0; i < 20; i++) {
			frame.append('=');
		}
		return frame.toString();
	}

}
