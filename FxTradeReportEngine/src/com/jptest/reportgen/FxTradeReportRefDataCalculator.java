package com.jptest.reportgen;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.TreeSet;

import org.joda.time.LocalDate;

import com.jptest.common.TradeAmountComparator;
import com.jptest.engine.factory.FxTradeEngineFactory;
import com.jptest.engine.trademanager.impl.FxTradeManagerImpl;
import com.jptest.model.TradeInstruction;
import com.jptest.model.TradeInstructionRank;
import com.jptest.model.TradeInstructionType;

//This class is used to calculate the reference data required for report generation
public class FxTradeReportRefDataCalculator {
	private FxTradeManagerImpl tradeManager = null; 
	private Set<TradeInstruction> outgoingTrades = new HashSet<TradeInstruction>();
	private Set<TradeInstruction> incomingTrades = new HashSet<TradeInstruction>();
	
	public FxTradeReportRefDataCalculator() {
		this.tradeManager = (FxTradeManagerImpl) FxTradeEngineFactory.getFxTradeManager();
		Set<TradeInstruction> masterTradeList = tradeManager.getFxTrades();
		
		for(TradeInstruction instruction : masterTradeList){
			if(TradeInstructionType.BUY.getBuySellFlag().equals(instruction.getType())){
				//populate into list of outgoing trades
				outgoingTrades.add(instruction);
			} else if(TradeInstructionType.SELL.getBuySellFlag().equals(instruction.getType())){
				//populate into list of incoming trades
				incomingTrades.add(instruction);
			}
		}
	}

	//Calculate total buy (outgoing) amount settled in a day
	public Map<LocalDate, BigDecimal> calculateDailyOutgoingAmountUSD(){
		Map<LocalDate, BigDecimal> dailyOutgoingAmountMap = new HashMap<LocalDate, BigDecimal>();
		if(outgoingTrades != null && outgoingTrades.size() > 0){
			dailyOutgoingAmountMap = calculateDailyAmount(outgoingTrades);
		}
		return dailyOutgoingAmountMap;
	} 
	
	//Calculate total sell (incoming) amount settled in a day
	public Map<LocalDate, BigDecimal> calculateDailyIncomingAmountUSD(){
		Map<LocalDate, BigDecimal> dailyIncomingAmountMap = new HashMap<LocalDate, BigDecimal>();
		if(incomingTrades != null && incomingTrades.size() > 0){
			dailyIncomingAmountMap = calculateDailyAmount(incomingTrades);
		}
		return dailyIncomingAmountMap;
	}
	
	//Calculate outgoing rank based on settlement date
	public Map<LocalDate, LinkedList<TradeInstructionRank>> calculateDailyOutgoingRank(){
		Map<LocalDate, LinkedList<TradeInstructionRank>> dailyOutgoingRankMap = new HashMap<LocalDate, LinkedList<TradeInstructionRank>>();
		if(outgoingTrades != null && outgoingTrades.size() > 0){
			dailyOutgoingRankMap = calculateDailyRank(outgoingTrades);
		}
		return dailyOutgoingRankMap;
	}
	
	//Calculate incoming rank based on settlement date
	public Map<LocalDate, LinkedList<TradeInstructionRank>> calculateDailyIncomingRank(){
		Map<LocalDate, LinkedList<TradeInstructionRank>> dailyIncomingRankMap = new HashMap<LocalDate, LinkedList<TradeInstructionRank>>();
		if(incomingTrades != null && incomingTrades.size() > 0){
			dailyIncomingRankMap = calculateDailyRank(incomingTrades);
		}
		return dailyIncomingRankMap;
	}
	
	private Map<LocalDate, BigDecimal> calculateDailyAmount(Set<TradeInstruction> instructions){
		Map<LocalDate, BigDecimal> dailyAmountMap = new HashMap<LocalDate, BigDecimal>();
		BigDecimal tradeSum = BigDecimal.ZERO;
		for(TradeInstruction instruction : instructions) {
			dailyAmountMap.put(instruction.getSettlementDate(),tradeSum.add(instruction.getTradeAmount()));
		}
		return dailyAmountMap;
	}
	
	private Map<LocalDate, LinkedList<TradeInstructionRank>> calculateDailyRank(Set<TradeInstruction> instructions){
		Set<TradeInstruction> tradeList = new TreeSet<TradeInstruction>(new TradeAmountComparator());
		tradeList.addAll(instructions);
		Map<LocalDate, LinkedList<TradeInstructionRank>> dailyRankMap = new HashMap<LocalDate, LinkedList<TradeInstructionRank>>();
		
		for(TradeInstruction instruction : tradeList){
			LocalDate dateKey = instruction.getSettlementDate();
			if(dailyRankMap.containsKey(dateKey)){				
				int lastRank = dailyRankMap.get(dateKey).getLast().getRank();
				TradeInstructionRank rank = new TradeInstructionRank(instruction.getEntity(), instruction.getSettlementDate(), lastRank+1);
				dailyRankMap.get(dateKey).add(rank);
			} else {
				LinkedList<TradeInstructionRank> rankList = new LinkedList<TradeInstructionRank>();
				TradeInstructionRank rank = new TradeInstructionRank(instruction.getEntity(), instruction.getSettlementDate(), 1);
				rankList.add(rank);
				dailyRankMap.put(instruction.getSettlementDate(), rankList);
			}
		}
		return dailyRankMap;
	}
}