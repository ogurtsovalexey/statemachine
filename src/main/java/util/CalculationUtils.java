package util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class CalculationUtils {

    public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_DOWN;

    public static BigDecimal divide(BigDecimal divisible, Integer intDivisor) {
        Objects.requireNonNull(divisible);
        Objects.requireNonNull(intDivisor);

        if (intDivisor == 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal divisor = BigDecimal.valueOf(intDivisor);

        divisible = divisible.setScale(3, ROUNDING_MODE);
        divisor = divisor.setScale(3, ROUNDING_MODE);

        return  divisible.divide(divisor, ROUNDING_MODE);
    }
}
