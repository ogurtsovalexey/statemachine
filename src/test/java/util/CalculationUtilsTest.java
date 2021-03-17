package util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CalculationUtilsTest {

    @Rule
    public ExpectedException npeRule = ExpectedException.none();

    @Test
    public void divide() {
        assertEquals(0, BigDecimal.ZERO.compareTo(CalculationUtils.divide(BigDecimal.ONE, 0)));
        assertEquals(0, BigDecimal.TEN.compareTo(CalculationUtils.divide(BigDecimal.valueOf(500), 50)));
        assertEquals(0, BigDecimal.valueOf(25.02).compareTo(CalculationUtils.divide(BigDecimal.valueOf(100.08), 4)));
    }

    @Test
    public void divideNPE() {
        npeRule.expect(NullPointerException.class);
        CalculationUtils.divide(null, null);
    }
}