package func;

import entity.CashBox;

@FunctionalInterface
public interface OccupationCalculator {
    Double calculate(CashBox cashBox);
}
