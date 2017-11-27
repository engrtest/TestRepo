package com.jptest.engine.datecalculator;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import com.jptest.common.DayOfWeek;
import org.joda.time.LocalDate;

public class NormalWorkingDayCalculator extends WorkingDayCalculator {
	
	private static NormalWorkingDayCalculator dayInstance = null;
	private static final Map<DayOfWeek,Boolean> normalWorkingDayMap = Collections.unmodifiableMap(
			new HashMap<DayOfWeek,Boolean>(){
				{	
				  put(DayOfWeek.MONDAY, true);
				  put(DayOfWeek.TUESDAY, true);
				  put(DayOfWeek.WEDNESDAY, true);
				  put(DayOfWeek.THURSDAY, true);
				  put(DayOfWeek.FRIDAY, true);
				  put(DayOfWeek.SATURDAY, false);
				  put(DayOfWeek.SUNDAY, false);
			    }
			});

	public static WorkingDayCalculator getInstance() {
		if (dayInstance == null) {
			dayInstance =  new NormalWorkingDayCalculator();
		}
		return dayInstance;
	}
	
	@Override
	public LocalDate getWorkingDayForSettlement(LocalDate settlementDate) {
		String settlementDay = settlementDate.dayOfWeek().getAsText().toUpperCase();
		DayOfWeek weekDay = DayOfWeek.valueOf(settlementDay);
		// return same date if it's valid working day as per days policy
		if(normalWorkingDayMap.get(weekDay)){
			return settlementDate;
		} else{ //otherwise check for next working day 
			return getWorkingDayForSettlement(settlementDate.plusDays(1));
		}
	}
}