package se.kth.iv1350.processofsale.view;

public class ErrorHandler {
	
	ErrorHandler(){
	}
	
	void showError(String msg) {
		StringBuilder errorMsg = new StringBuilder();
		String frame = buildFrame(msg);
		errorMsg.append("ERROR\n");
		errorMsg.append(frame+"\n");
		errorMsg.append(msg+"\n");
		errorMsg.append(frame);
		System.err.println(errorMsg);
	}
	
	private String buildFrame(String msg) {
		StringBuilder frame = new StringBuilder();
		for(int i = 0; i<msg.length();i++) {
			frame.append("--");
		}
		return frame.toString();
	}
	
}
