package com.jptest.engine.validator.util;

import org.joda.time.LocalDate;
import com.jptest.exception.InvalidTradeException;
import com.jptest.exception.InvalidTradeDetailsException;

public class DateValidator {
	
	//check if given date is past date or null
	public static boolean isValidDate(LocalDate date) throws InvalidTradeException {
		if(date == null || date.isBefore(LocalDate.now())) {
			throw new InvalidTradeDetailsException("Given trade date is invalid");
		}	
		return true;
	}
}
