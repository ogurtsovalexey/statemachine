package factory;

import entity.CashBox;
import util.CalculationUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CashBoxFactory {

    private Function<Integer, BigDecimal> calcProcessSpeedFunc = (throughput) ->
            CalculationUtils.divide(BigDecimal.valueOf(60), throughput);

    public Collection<CashBox> createCashBoxes(Map<String, Integer> params) {
        return params.entrySet()
                .stream()
                .map((entry) -> new CashBox(entry.getKey(), calcProcessSpeedFunc.apply(entry.getValue())))
                .collect(Collectors.toSet());
    }
}
