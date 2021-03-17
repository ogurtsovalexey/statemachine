package service;

import entity.CashBox;
import func.OccupationCalculator;

import java.util.*;
import java.util.stream.Collectors;

public class QueueServiceImpl implements QueueService {

    private final PriorityQueue<CashBox> cashBoxPriorityQueue;
    private final int eachCashBoxCapacity;

    public QueueServiceImpl(int eachCashBoxCapacity, Collection<CashBox> cashBoxes, OccupationCalculator occupationCalculator) {
        this.eachCashBoxCapacity = eachCashBoxCapacity;
        this.cashBoxPriorityQueue = new PriorityQueue<>(Comparator.comparingDouble(occupationCalculator::calculate));
        this.cashBoxPriorityQueue.addAll(cashBoxes);
    }

    @Override
    public String addToQueue() {
        CashBox cashBox = cashBoxPriorityQueue.poll();
        if (cashBox == null) {
            return cashBoxPriorityQueue.size() > 0
                    ? "This command does not exist. Try again"
                    : "Sorry, no available cashbox";
        }

        if (cashBox.getBuyerCount() >= eachCashBoxCapacity) {
            String message = addToQueue();
            cashBoxPriorityQueue.offer(cashBox);
            return message;
        }

        cashBox.incrementBuyerCount();
        cashBoxPriorityQueue.offer(cashBox);
        return String.format("Customer was placed at the %s cashbox", cashBox.getName());
    }

    @Override
    public String removeFromQueueIfExists(String queueNumber) {
        Optional<CashBox> cashBoxOpt = cashBoxPriorityQueue
                .stream()
                .filter(e -> queueNumber.equals(e.getName()))
                .findFirst();

        if (!cashBoxOpt.isPresent()) {
            return "CashBox with this name does not exist. Try again";
        }

        CashBox cashBox = cashBoxOpt.get();
        if (cashBox.getBuyerCount() <= 0) {
            return String.format("No any customers at the cashbox %s", cashBox.getName());
        }
        cashBoxPriorityQueue.remove(cashBox);
        cashBox.decrementBuyerCount();
        cashBoxPriorityQueue.offer(cashBox);

        return String.format("Customer was placed from cashbox %s", cashBox.getName());
    }

    @Override
    public List<CashBox> getAllCashBoxes() {
        return cashBoxPriorityQueue.stream().sorted(Comparator.comparing(CashBox::getName)).collect(Collectors.toList());
    }
}
