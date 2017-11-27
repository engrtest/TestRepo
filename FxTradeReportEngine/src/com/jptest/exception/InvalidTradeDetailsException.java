package com.jptest.exception;

// Exception for invalid trade parameters received from client
public class InvalidTradeDetailsException extends InvalidTradeException {
	private static final long serialVersionUID = 1L;

	public InvalidTradeDetailsException() {
		super();
	}
	
	public InvalidTradeDetailsException(String message) {
		super(message);
	}
	
	public InvalidTradeDetailsException(String message, Exception ex) {
		super(message, ex);
	}
}
