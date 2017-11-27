package com.jptest.engine.trademanager.impl;

import com.jptest.model.TradeInstruction;
import com.jptest.engine.datecalculator.TradeSettlementDateCalculator;
import com.jptest.engine.factory.FxTradeEngineFactory;
import com.jptest.engine.trademanager.FxTradeManager;
import com.jptest.engine.validator.FxTradeValidator;
import com.jptest.exception.DuplicateTradeException;
import com.jptest.exception.InvalidTradeException;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.List;

public class FxTradeManagerImpl implements FxTradeManager {
	
	private static final Set<TradeInstruction> tradeStorage = new LinkedHashSet<TradeInstruction>();
	
	private static FxTradeManagerImpl manager = null;
	FxTradeValidator validator = FxTradeEngineFactory.getFxTradeValidator();
	
	public static FxTradeManager getInstance(){
		if(manager == null){
			manager = new FxTradeManagerImpl();
		}
		return manager;
	}
	
	public void submitFxTrades(List<TradeInstruction> instructions) {
		TradeInstruction problematicTrade = null;
		//trade validation for a list of incoming trades from client
		for(TradeInstruction instruction : instructions){
			try{
				problematicTrade = instruction;
				submitFxTrade(instruction);
			}catch(InvalidTradeException ex){
				System.out.println("Problematic trade entity: "+problematicTrade+" with error message: "+ex.getLocalizedMessage()+", so not acceptable");
			}	
		}
	}
	
	private void submitFxTrade(TradeInstruction instruction) throws InvalidTradeException {
		//trade validation for incoming trade from client
		boolean isValidTrade = validator.validateTrade(instruction);
		if(isValidTrade){ // valid trade as per validation criteria, so accepted by trade engine
			//calculate amount for trade and update trade instruction object
			BigDecimal tradeAmount = calculateTradeAmount(instruction);
			instruction.setTradeAmount(tradeAmount);
			
			//update trade amount in trade instruction object
			TradeSettlementDateCalculator.calculateAndUpdateSettlementDateForTrade(instruction);
			//add this trade to in-memory in trade's master storage and complain for duplicate trades
			if(!tradeStorage.add(instruction)){
				throw new DuplicateTradeException("Duplicate Trade found, so not acceptable");
			}
		}
	}
	
	public Set<TradeInstruction> getFxTrades(){
		return Collections.unmodifiableSet(tradeStorage);
	}
	
	private BigDecimal calculateTradeAmount(TradeInstruction instruction){
		  return instruction.getPricePerUnit().multiply(BigDecimal.valueOf(instruction.getUnits())).multiply(instruction.getAgreedFx());		  			
	}
	
}