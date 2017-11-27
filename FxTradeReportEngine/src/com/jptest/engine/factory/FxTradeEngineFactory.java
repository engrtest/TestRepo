package com.jptest.engine.factory;

import com.jptest.engine.validator.FxTradeValidator;
import com.jptest.engine.validator.impl.FxTradeValidatorImpl;
import com.jptest.engine.trademanager.FxTradeManager;
import com.jptest.engine.trademanager.impl.FxTradeManagerImpl;

public class FxTradeEngineFactory {
	
	public static FxTradeValidator getFxTradeValidator() {
		return FxTradeValidatorImpl.getInstance();
	}
	
	public static FxTradeManager getFxTradeManager() {
		return FxTradeManagerImpl.getInstance();
	}
}
