package com.jptest.common;

import java.util.Comparator;
import com.jptest.model.TradeInstruction;

public class TradeAmountComparator implements Comparator<TradeInstruction> {
	
	@Override
	public int compare(TradeInstruction inst1, TradeInstruction inst2) {
		return inst2.getTradeAmount().compareTo(inst1.getTradeAmount());
	}
}