package com.jptest.engine.datecalculator;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.joda.time.LocalDate;
import com.jptest.common.DayOfWeek;

public class SpecialWorkingDayCalculator extends WorkingDayCalculator {

	private static SpecialWorkingDayCalculator dayInstance = null;
	private static final Map<DayOfWeek,Boolean> specialWorkingDayMap = Collections.unmodifiableMap(
			new HashMap<DayOfWeek,Boolean>(){
				{	
				  put(DayOfWeek.MONDAY, true);
				  put(DayOfWeek.TUESDAY, true);
				  put(DayOfWeek.WEDNESDAY, true);
				  put(DayOfWeek.THURSDAY, true);
				  put(DayOfWeek.FRIDAY, false);
				  put(DayOfWeek.SATURDAY, false);
				  put(DayOfWeek.SUNDAY, true);
			    }
			});
	
	public static WorkingDayCalculator getInstance() {
		if (dayInstance == null) {
			dayInstance =  new SpecialWorkingDayCalculator();
		}
		return dayInstance;
	}

	@Override
	public LocalDate getWorkingDayForSettlement(LocalDate settlementDate) {
		String settlementDay = settlementDate.dayOfWeek().getAsText().toUpperCase();
		DayOfWeek weekDay = DayOfWeek.valueOf(settlementDay);
		// return same date if it's valid working day as per days policy
		if(specialWorkingDayMap.get(weekDay)){
			return settlementDate;
		} else{ //otherwise check for next working day 
			return getWorkingDayForSettlement(settlementDate.plusDays(1));
		}
	}
}
