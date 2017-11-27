package com.jptest.reportgen.impl;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Map;

import org.joda.time.LocalDate;

import com.jptest.model.TradeInstructionRank;
import com.jptest.reportgen.FxTradeReportRefDataCalculator;
import com.jptest.reportgen.ReportGenerator;

public class FxTradeReportGeneratorImpl implements ReportGenerator {
	
	private FxTradeReportRefDataCalculator refData = null;
	private StringBuilder reportBuilder = new StringBuilder();
	
	public FxTradeReportGeneratorImpl() {
		this.refData = new FxTradeReportRefDataCalculator();
	}

	@Override
	public void generateReport() {
		//Build report console format only for valid trade instructions as per validation criteria
		reportBuilder.append("\n----------------------------------------\n")
		.append("         Daily Outgoing Amount in USD          \n")
		.append("----------------------------------------\n")
		.append("      Date       |    Trade Amount      \n")
		.append("----------------------------------------\n");

		Map<LocalDate, BigDecimal> outgoingAmountMap = refData.calculateDailyOutgoingAmountUSD();
		for(Map.Entry<LocalDate, BigDecimal> entry : outgoingAmountMap.entrySet()){
			reportBuilder.append(entry.getKey()).append("      |    ").append(entry.getValue()).append("\n");
		}
		
		reportBuilder.append("\n----------------------------------------\n")
		.append("         Daily Incoming Amount in USD          \n")
		.append("----------------------------------------\n")
		.append("      Date       |    Trade Amount      \n")
		.append("----------------------------------------\n");

		Map<LocalDate, BigDecimal> incomingAmountMap = refData.calculateDailyIncomingAmountUSD();
		for(Map.Entry<LocalDate, BigDecimal> entry : incomingAmountMap.entrySet()){
			reportBuilder.append(entry.getKey()).append("      |    ").append(entry.getValue()).append("\n");
		}

		reportBuilder.append("\n----------------------------------------\n")
		.append("         Daily Outgoing Rank          \n")
		.append("----------------------------------------\n")
		.append("      Date       |   Entity  | Rank      \n")
		.append("----------------------------------------\n");
		
		Map<LocalDate, LinkedList<TradeInstructionRank>> outgoingRankMap = refData.calculateDailyOutgoingRank();
		for(LocalDate date : outgoingRankMap.keySet()){
			for(TradeInstructionRank rank : outgoingRankMap.get(date)){
				reportBuilder.append(date).append("      |    ").append(rank.getEntity()).append("      |    ").append(rank.getRank()).append("\n");
			}
		}
		
		reportBuilder.append("\n----------------------------------------\n")
		.append("         Daily Incoming Rank          \n")
		.append("----------------------------------------\n")
		.append("      Date       |   Entity  | Rank      \n")
		.append("----------------------------------------\n");
		
		Map<LocalDate, LinkedList<TradeInstructionRank>> incomingRankMap = refData.calculateDailyIncomingRank();
		for(LocalDate date : incomingRankMap.keySet()){
			for(TradeInstructionRank rank : incomingRankMap.get(date)){
				reportBuilder.append(date).append("      |    ").append(rank.getEntity()).append("      |    ").append(rank.getRank()).append("\n");
			}
		}
		
		System.out.println(reportBuilder.toString());			
	}

}
