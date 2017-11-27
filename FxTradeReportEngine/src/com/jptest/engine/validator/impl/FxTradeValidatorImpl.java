package com.jptest.engine.validator.impl;

import com.jptest.model.TradeInstruction;
import com.jptest.engine.validator.FxTradeValidator;
import com.jptest.exception.InvalidTradeException;
import com.jptest.engine.validator.util.*;

public class FxTradeValidatorImpl implements FxTradeValidator {
	
	private static FxTradeValidatorImpl validator = null;
	
	public static FxTradeValidator getInstance(){
		if(validator == null){
			validator = new FxTradeValidatorImpl();
		}
		return validator;
	}
	
	@Override
	public boolean validateTrade(TradeInstruction instruction)throws InvalidTradeException {
		boolean isValid = false;
		boolean isDateValidated = DateValidator.isValidDate(instruction.getInstructionDate());
		boolean isSettlementDateValidated = DateValidator.isValidDate(instruction.getSettlementDate());
		boolean isCurrencyValid = CurrencyCodeValidator.validateCurrency(instruction.getCurrency());
		boolean isTradeTypeValid = TradeTypeValidator.isValidTradeType(instruction.getType());
			
		if(isDateValidated && isSettlementDateValidated && isCurrencyValid && isTradeTypeValid){
				isValid = true;
		}
		return isValid;
	}
}
