package tests;

import static org.junit.Assert.*;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import com.jptest.engine.datecalculator.SpecialWorkingDayCalculator;
import com.jptest.engine.datecalculator.WorkingDayCalculator;

public class SpecialWorkingDayCalculatorTest {
	private WorkingDayCalculator wdc;
	
	@Before
	public void setup() throws Exception {
		wdc = SpecialWorkingDayCalculator.getInstance();
	}
	
	@Test
	public void testGetWorkingDayForSettlement(){
		LocalDate date = LocalDate.parse("2017-11-28");
		assertEquals(date, wdc.getWorkingDayForSettlement(date));
	}
	
	@Test
	public void testGetWorkingDayForSettlementOnFriday(){
		LocalDate date = LocalDate.parse("2017-12-01");
		assertEquals(LocalDate.parse("2017-12-03"), wdc.getWorkingDayForSettlement(date));
	}
}
