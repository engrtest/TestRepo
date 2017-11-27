package com.jptest.model;

import java.math.BigDecimal;
import org.joda.time.LocalDate;

// Model to represent trade instruction received from client and this raw info. shouldn't be subject to change
public class TradeInstruction {

	private final String entity;
	private final String type;
	private final BigDecimal agreedFx;
	private final String currency;
	private final LocalDate instructionDate;
	
	// settlement date is subject to change
	private LocalDate settlementDate;
	
	private final int noOfUnits;
	private final BigDecimal pricePerUnit;
	
	// represent USD calculated trade amount
	private BigDecimal tradeAmount;
	
	// This trade instruction is required from client in request
	public TradeInstruction(String entity, String type, 
							BigDecimal agreedFx, String currency,
							LocalDate instructionDate, LocalDate settlementDate,
							int noOfUnits, BigDecimal pricePerUnit) {
		this.entity = entity;
		this.type = type;
		this.agreedFx = agreedFx;
		this.currency = currency;
		this.instructionDate = instructionDate;
		this.settlementDate = settlementDate;
		this.noOfUnits = noOfUnits;
		this.pricePerUnit = pricePerUnit;
	}
	
	public String getEntity() {
		return entity;
	}
	
	public String getType() {
		return type;
	}
	
	public BigDecimal getAgreedFx() {
		return agreedFx;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public LocalDate getInstructionDate() {
		return instructionDate;
	}
	
	public LocalDate getSettlementDate() {
		return settlementDate;
	}
	
	public void setSettlementDate(LocalDate settlementDate) {
		this.settlementDate = settlementDate;
	}
	
	public int getUnits() {
		return noOfUnits;
	}
	
	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}

	public void setTradeAmount(BigDecimal tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public BigDecimal getTradeAmount() {
		return tradeAmount;
	}
	
	@Override
	public boolean equals(Object ob){
		if(ob == null || (this.getClass() != ob.getClass())){
			return false;
		}
		TradeInstruction another = (TradeInstruction)ob;
		boolean isEquals = this.getEntity().equals(another.getEntity()) && this.getCurrency().equals(another.getCurrency()) 
				&& this.getInstructionDate().equals(another.getInstructionDate()) 
				&& this.getSettlementDate().equals(another.getSettlementDate())
				&& this.getAgreedFx().doubleValue() == another.getAgreedFx().doubleValue()
				&& this.getPricePerUnit().doubleValue() == another.getPricePerUnit().doubleValue()
				&& this.getType().equals(another.getType()) && this.getUnits() == another.getUnits();
		return isEquals;
	}
	
	@Override
	public String toString(){
		return "Entity: "+this.entity+", Type: "+this.type+", Currency: "+this.currency+", AgreedFx: "+this.agreedFx+", InstructionDate: "+this.instructionDate+", SettlementDate: "+this.settlementDate+", noOfUnits: "+this.noOfUnits+", PricePerUnit: "+this.pricePerUnit;
		
	}
}