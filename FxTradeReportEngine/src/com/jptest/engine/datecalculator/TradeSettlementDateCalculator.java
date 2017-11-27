package com.jptest.engine.datecalculator;
import org.joda.time.LocalDate;
import com.jptest.model.TradeInstruction;

public class TradeSettlementDateCalculator {
	
	public static void calculateAndUpdateSettlementDateForTrade(TradeInstruction instruction) {
		if(instruction != null) {
			//get working day helper based on currency
			WorkingDayCalculator strategy = findWorkingDayCalculator(instruction.getCurrency());
			//calculate settlement date according to currency working days
			LocalDate newSettlementDate = strategy.getWorkingDayForSettlement(instruction.getSettlementDate());
			//update settlement date in trade instruction object
			if(newSettlementDate != null){
				instruction.setSettlementDate(newSettlementDate);
			}	
		}	
	}
	
	private static WorkingDayCalculator findWorkingDayCalculator(String currency){
		if("AED".equals(currency) || "SAR".equals(currency)){
			return SpecialWorkingDayCalculator.getInstance();
		}
		return NormalWorkingDayCalculator.getInstance();
	}
}
