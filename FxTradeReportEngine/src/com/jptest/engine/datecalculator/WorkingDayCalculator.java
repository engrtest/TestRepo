package com.jptest.engine.datecalculator;
import org.joda.time.LocalDate;

public abstract class WorkingDayCalculator {
	public abstract LocalDate getWorkingDayForSettlement(LocalDate settlementDate);	
}
