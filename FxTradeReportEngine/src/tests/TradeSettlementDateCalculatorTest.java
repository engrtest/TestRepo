package tests;

import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import com.jptest.engine.datecalculator.TradeSettlementDateCalculator;
import com.jptest.model.TradeInstruction;

public class TradeSettlementDateCalculatorTest {
	TradeSettlementDateCalculator dateCalculator;
	
	@Before
	public void setup() throws Exception {
	
	}
	
	@Test
	public void testCalculateAndUpdateSettlementDateForTradeInSAR(){
		TradeInstruction td = new TradeInstruction("foo", "S", BigDecimal.valueOf(0.5), "SAR", LocalDate.parse("2017-11-30"), LocalDate.parse("2017-12-01"), 250, BigDecimal.valueOf(122.05));
		TradeSettlementDateCalculator.calculateAndUpdateSettlementDateForTrade(td);
		assertEquals(LocalDate.parse("2017-12-03"), td.getSettlementDate());
	}
	
	@Test
	public void testCalculateAndUpdateSettlementDateForTradeInEUR(){
		TradeInstruction td = new TradeInstruction("bar", "B", BigDecimal.valueOf(0.5), "EUR", LocalDate.parse("2017-11-29"), LocalDate.parse("2017-12-01"), 150, BigDecimal.valueOf(105.35));
		TradeSettlementDateCalculator.calculateAndUpdateSettlementDateForTrade(td);
		assertEquals(LocalDate.parse("2017-12-01"), td.getSettlementDate());
	}
}
