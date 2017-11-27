package com.jptest.engine.validator;

import com.jptest.model.TradeInstruction;
import com.jptest.exception.InvalidTradeException;

public interface FxTradeValidator {
	 public boolean validateTrade(TradeInstruction instruction) throws InvalidTradeException;
}
