package com.jptest.client;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.jptest.engine.trademanager.FxTradeManager;
import com.jptest.engine.trademanager.impl.FxTradeManagerImpl;
import com.jptest.model.TradeInstruction;
import com.jptest.reportgen.ReportGenerator;
import com.jptest.reportgen.impl.FxTradeReportGeneratorImpl;

import org.joda.time.LocalDate;

public class FxTradeReportEngineClient {
	private static FxTradeManager tradeManager = FxTradeManagerImpl.getInstance();

	public static void main(String[] args) {
		List<TradeInstruction> tradesToExecute = tradeSampleData();
		tradeManager.submitFxTrades(tradesToExecute);
		
		//Now generate report as per need
		ReportGenerator reportgen = new FxTradeReportGeneratorImpl();
		reportgen.generateReport();
	}
	
	//Sample data to test trade reporting engine. This data contains duplicate trades, invalid currency trades and invalid type trades also
	private static List<TradeInstruction> tradeSampleData(){
		List<TradeInstruction> tradesToExecute = new ArrayList<TradeInstruction>();
		
		TradeInstruction td1 = new TradeInstruction("foo", "B", BigDecimal.valueOf(0.5), "SGP", LocalDate.parse("2017-11-28"), LocalDate.parse("2017-11-29"), 200, BigDecimal.valueOf(100.25));
		TradeInstruction td2 = new TradeInstruction("bar", "S", BigDecimal.valueOf(0.22), "AED", LocalDate.parse("2017-11-30"), LocalDate.parse("2017-01-12"), 450, BigDecimal.valueOf(150.5));
		TradeInstruction td3 = new TradeInstruction("xyz", "B", BigDecimal.valueOf(0.28), "AUD", LocalDate.parse("2017-11-28"), LocalDate.parse("2017-11-29"), 200, BigDecimal.valueOf(120.5));
		TradeInstruction td4 = new TradeInstruction("foo", "S", BigDecimal.valueOf(0.5), "SAR", LocalDate.parse("2017-11-30"), LocalDate.parse("2017-12-01"), 250, BigDecimal.valueOf(122.05));
		TradeInstruction td5 = new TradeInstruction("bar", "B", BigDecimal.valueOf(0.5), "EUR", LocalDate.parse("2017-11-29"), LocalDate.parse("2017-11-30"), 150, BigDecimal.valueOf(105.35));
		TradeInstruction td6 = new TradeInstruction("bar", "S", BigDecimal.valueOf(0.5), "GBP", LocalDate.parse("2017-11-27"), LocalDate.parse("2017-11-28"), 150, BigDecimal.valueOf(106.35));
		TradeInstruction td7 = new TradeInstruction("xyz", "S", BigDecimal.valueOf(0.5), "EUR", LocalDate.parse("2017-12-01"), LocalDate.parse("2017-12-02"), 150, BigDecimal.valueOf(125.35));
		TradeInstruction td8 = new TradeInstruction("bar", "U", BigDecimal.valueOf(0.22), "AED", LocalDate.parse("2017-11-30"), LocalDate.parse("2017-12-01"), 450, BigDecimal.valueOf(150.5));
		TradeInstruction td9 = new TradeInstruction("bar", "B", BigDecimal.valueOf(0.5), "EUR", LocalDate.parse("2017-11-29"), LocalDate.parse("2017-11-30"), 150, BigDecimal.valueOf(105.35));
		TradeInstruction td10 = new TradeInstruction("abc", "B", BigDecimal.valueOf(0.35), "AUD", LocalDate.parse("2017-11-29"), LocalDate.parse("2017-11-30"), 200, BigDecimal.valueOf(150.5));
		TradeInstruction td11 = new TradeInstruction("foo", "S", BigDecimal.valueOf(0.40), "EUR", LocalDate.parse("2017-12-01"), LocalDate.parse("2017-12-02"), 200, BigDecimal.valueOf(110.5));
		TradeInstruction td12 = new TradeInstruction("exp", "S", BigDecimal.valueOf(0.30), "SGD", LocalDate.parse("2017-12-01"), LocalDate.parse("2017-12-02"), 200, BigDecimal.valueOf(130.5));
		
		tradesToExecute.add(td1);
		tradesToExecute.add(td2);
		tradesToExecute.add(td3);
		tradesToExecute.add(td4);
		tradesToExecute.add(td5);
		tradesToExecute.add(td6);
		tradesToExecute.add(td7);
		tradesToExecute.add(td8);
		tradesToExecute.add(td3);
		tradesToExecute.add(td7);
		tradesToExecute.add(td9);
		tradesToExecute.add(td10);
		tradesToExecute.add(td11);
		tradesToExecute.add(td12);
		
		return tradesToExecute;
	}

}
