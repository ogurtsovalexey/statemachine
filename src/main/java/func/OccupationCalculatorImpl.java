package func;

import entity.CashBox;

public class OccupationCalculatorImpl implements OccupationCalculator {

    @Override
    public Double calculate(CashBox cashBox) {
        if (cashBox.getBuyerCount() > 0) {
            return (cashBox.getBuyerCount() + 1) * cashBox.getTimePerOneCustomer().doubleValue();
        }
        return cashBox.getTimePerOneCustomer().doubleValue();
    }
}
