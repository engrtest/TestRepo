package com.jptest.engine.validator.util;

import java.util.Currency;
import com.jptest.exception.InvalidTradeException;
import com.jptest.exception.InvalidTradeDetailsException;

public class CurrencyCodeValidator {
	
	public static boolean validateCurrency(String currency) throws InvalidTradeException {
		try {
			Currency.getInstance(currency);
		} catch(Exception ex) {
			throw new InvalidTradeDetailsException("Invalid Currency code");
		}	
		return true;
	}
}
