package entity;

import java.math.BigDecimal;

public class CashBox {
    private final String name;

    private final BigDecimal timePerOneCustomer;
    private int buyerCount;

    public CashBox(String name, BigDecimal timePerOneCustomer) {
        this.name = name;
        this.timePerOneCustomer = timePerOneCustomer;
        this.buyerCount = 0;
    }

    public BigDecimal getTimePerOneCustomer() {
        return timePerOneCustomer;
    }

    private double getOccupation() {
        return buyerCount != 0 ? (buyerCount + 1) * timePerOneCustomer.doubleValue() : timePerOneCustomer.doubleValue();
    }

    public void incrementBuyerCount() {
        this.buyerCount++;
    }

    public void decrementBuyerCount() {
        if (this.buyerCount > 0) {
            this.buyerCount--;
        }
    }

    public String getName() {
        return name;
    }

    public int getBuyerCount() {
        return buyerCount;
    }

    @Override
    public String toString() {
        return "CashBox{" +
                "name=" + name +
                ", time to wait for next=" + getOccupation() +
                ", buyerCount=" + buyerCount +
                '}';
    }
}
