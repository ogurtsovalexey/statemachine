package func;

import entity.CashBox;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OccupationCalculatorImplTest {

    @Test
    public void testCalculate() {
        CashBox cashBoxFirst =  new CashBox("Dummy1", BigDecimal.valueOf(60));
        CashBox cashBoxSecond =  new CashBox("Dummy2", BigDecimal.valueOf(30.01));
        CashBox cashBoxThird =  new CashBox("Dummy3", BigDecimal.valueOf(17.8));

        OccupationCalculator calculator = new OccupationCalculatorImpl();
        assertEquals(60.0, calculator.calculate(cashBoxFirst), 0.0);
        assertEquals(30.01, calculator.calculate(cashBoxSecond), 0.0);
        assertEquals(17.8, calculator.calculate(cashBoxThird), 0.0);


        cashBoxFirst.incrementBuyerCount();
        cashBoxSecond.incrementBuyerCount();
        cashBoxThird.incrementBuyerCount();
        assertEquals(120.0, calculator.calculate(cashBoxFirst), 0.0);
        assertEquals(60.02, calculator.calculate(cashBoxSecond), 0.0);
        assertEquals(35.6, calculator.calculate(cashBoxThird), 0.0);
    }
}