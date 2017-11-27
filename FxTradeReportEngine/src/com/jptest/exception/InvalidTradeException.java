package com.jptest.exception;

// Exception for invalid trades received from client
public class InvalidTradeException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidTradeException() {
		super();
	}
	
	public InvalidTradeException(String message) {
		super(message);
	}
	
	public InvalidTradeException(String message, Exception ex) {
		super(message, ex);
	}
}
