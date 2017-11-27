package com.jptest.engine.validator.util;

import com.jptest.exception.InvalidTradeException;
import com.jptest.exception.InvalidTradeDetailsException;
import com.jptest.model.TradeInstructionType;

public class TradeTypeValidator {
	
	public static boolean isValidTradeType(String buySellFlag)throws InvalidTradeException {
		boolean foundTradeType = false;
		for(TradeInstructionType type : TradeInstructionType.values()) {
	      if(type.getBuySellFlag().equalsIgnoreCase(buySellFlag)) {
	    	  foundTradeType = true;
	    	  break;
	      }
		}
		if(!foundTradeType){
			throw new InvalidTradeDetailsException("Given trade type is invalid");
		}	
		return foundTradeType;
	}	
}
