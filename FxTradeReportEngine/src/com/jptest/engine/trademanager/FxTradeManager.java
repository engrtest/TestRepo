package com.jptest.engine.trademanager;

import java.util.List;
import com.jptest.model.TradeInstruction;

public interface FxTradeManager {
	//API exposed to submit a list of trades to Fx trade engine
	public void submitFxTrades(List<TradeInstruction> instructions);
}
