package com.jptest.exception;

// Exception for duplicate trades received from client
public class DuplicateTradeException extends InvalidTradeException {
	private static final long serialVersionUID = 1L;

	public DuplicateTradeException() {
		super();
	}
	
	public DuplicateTradeException(String message) {
		super(message);
	}
	
	public DuplicateTradeException(String message, Exception ex) {
		super(message, ex);
	}
}
