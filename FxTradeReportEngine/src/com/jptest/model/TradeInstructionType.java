package com.jptest.model;

public enum TradeInstructionType {
	BUY("B"), SELL("S");
	
	private String buySellFlag;
	
	TradeInstructionType(String buySellFlag) {
		this.buySellFlag = buySellFlag;
	}
	
	public String getBuySellFlag() {
		return this.buySellFlag;
	}
}