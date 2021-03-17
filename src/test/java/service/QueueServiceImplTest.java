package service;

import entity.CashBox;
import factory.CashBoxFactory;
import func.OccupationCalculatorImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class QueueServiceImplTest {

    @Test
    public void testAddToQueue() {

        CashBoxFactory cashBoxFactory = new CashBoxFactory();
        Map<String, Integer> cashBoxesParam = new HashMap<>();
        cashBoxesParam.put("1", 10);
        cashBoxesParam.put("2", 13);
        cashBoxesParam.put("3", 15);
        cashBoxesParam.put("4", 17);
        Collection<CashBox> cashBoxes = cashBoxFactory.createCashBoxes(cashBoxesParam);

        QueueService queueService = new QueueServiceImpl(
                3,
                cashBoxes,
                new OccupationCalculatorImpl());


        queueService.addToQueue();
        Assert.assertEquals("0001", cashBoxes.stream()
                .sorted(Comparator.comparing(CashBox::getName))
                .map(cb -> String.valueOf(cb.getBuyerCount()))
                .reduce(String::concat).get());

        queueService.addToQueue();
        Assert.assertEquals("0011", cashBoxes.stream()
                .sorted(Comparator.comparing(CashBox::getName))
                .map(cb -> String.valueOf(cb.getBuyerCount()))
                .reduce(String::concat).get());

        queueService.addToQueue();
        Assert.assertEquals("0111", cashBoxes.stream()
                .sorted(Comparator.comparing(CashBox::getName))
                .map(cb -> String.valueOf(cb.getBuyerCount()))
                .reduce(String::concat).get());

        queueService.addToQueue();
        Assert.assertEquals("1111", cashBoxes.stream()
                .sorted(Comparator.comparing(CashBox::getName))
                .map(cb -> String.valueOf(cb.getBuyerCount()))
                .reduce(String::concat).get());

        queueService.addToQueue();
        Assert.assertEquals("1112", cashBoxes.stream()
                .sorted(Comparator.comparing(CashBox::getName))
                .map(cb -> String.valueOf(cb.getBuyerCount()))
                .reduce(String::concat).get());

        queueService.addToQueue();
        Assert.assertEquals("1122", cashBoxes.stream()
                .sorted(Comparator.comparing(CashBox::getName))
                .map(cb -> String.valueOf(cb.getBuyerCount()))
                .reduce(String::concat).get());

        queueService.addToQueue();
        Assert.assertEquals("1222", cashBoxes.stream()
                .sorted(Comparator.comparing(CashBox::getName))
                .map(cb -> String.valueOf(cb.getBuyerCount()))
                .reduce(String::concat).get());

        queueService.addToQueue();
        Assert.assertEquals("1223", cashBoxes.stream()
                .sorted(Comparator.comparing(CashBox::getName))
                .map(cb -> String.valueOf(cb.getBuyerCount()))
                .reduce(String::concat).get());

        queueService.addToQueue();
        Assert.assertEquals("1233", cashBoxes.stream()
                .sorted(Comparator.comparing(CashBox::getName))
                .map(cb -> String.valueOf(cb.getBuyerCount()))
                .reduce(String::concat).get());

        queueService.addToQueue();
        Assert.assertEquals("2233", cashBoxes.stream()
                .sorted(Comparator.comparing(CashBox::getName))
                .map(cb -> String.valueOf(cb.getBuyerCount()))
                .reduce(String::concat).get());

        queueService.addToQueue();
        Assert.assertEquals("2333", cashBoxes.stream()
                .sorted(Comparator.comparing(CashBox::getName))
                .map(cb -> String.valueOf(cb.getBuyerCount()))
                .reduce(String::concat).get());

        queueService.addToQueue();
        Assert.assertEquals("3333", cashBoxes.stream()
                .sorted(Comparator.comparing(CashBox::getName))
                .map(cb -> String.valueOf(cb.getBuyerCount()))
                .reduce(String::concat).get());


        queueService.addToQueue();
        Assert.assertEquals("3333", cashBoxes.stream()
                .sorted(Comparator.comparing(CashBox::getName))
                .map(cb -> String.valueOf(cb.getBuyerCount()))
                .reduce(String::concat).get());
    }

    @Test
    public void removeFromQueueIfExists() {
        CashBoxFactory cashBoxFactory = new CashBoxFactory();
        Map<String, Integer> cashBoxesParam = new HashMap<>();
        cashBoxesParam.put("1", 10);
        cashBoxesParam.put("2", 13);
        cashBoxesParam.put("3", 15);
        cashBoxesParam.put("4", 17);
        Collection<CashBox> cashBoxes = cashBoxFactory.createCashBoxes(cashBoxesParam);

        QueueService queueService = new QueueServiceImpl(
                3,
                cashBoxes,
                new OccupationCalculatorImpl());

        Assert.assertEquals("0000", cashBoxes.stream()
                .sorted(Comparator.comparing(CashBox::getName))
                .map(cb -> String.valueOf(cb.getBuyerCount()))
                .reduce(String::concat).get());

        // Add 3 customers to each cashbox
        for (int i = 0; i < 11; i++) {
            queueService.addToQueue();
        }

        queueService.addToQueue();
        Assert.assertEquals("3333", cashBoxes.stream()
                .sorted(Comparator.comparing(CashBox::getName))
                .map(cb -> String.valueOf(cb.getBuyerCount()))
                .reduce(String::concat).get());


        queueService.removeFromQueueIfExists("1");
        Assert.assertEquals("2333", cashBoxes.stream()
                .sorted(Comparator.comparing(CashBox::getName))
                .map(cb -> String.valueOf(cb.getBuyerCount()))
                .reduce(String::concat).get());

        queueService.removeFromQueueIfExists("4");
        Assert.assertEquals("2332", cashBoxes.stream()
                .sorted(Comparator.comparing(CashBox::getName))
                .map(cb -> String.valueOf(cb.getBuyerCount()))
                .reduce(String::concat).get());

        queueService.removeFromQueueIfExists("4");
        Assert.assertEquals("2331", cashBoxes.stream()
                .sorted(Comparator.comparing(CashBox::getName))
                .map(cb -> String.valueOf(cb.getBuyerCount()))
                .reduce(String::concat).get());

        queueService.removeFromQueueIfExists("4");
        Assert.assertEquals("2330", cashBoxes.stream()
                .sorted(Comparator.comparing(CashBox::getName))
                .map(cb -> String.valueOf(cb.getBuyerCount()))
                .reduce(String::concat).get());

        queueService.removeFromQueueIfExists("4");
        Assert.assertEquals("2330", cashBoxes.stream()
                .sorted(Comparator.comparing(CashBox::getName))
                .map(cb -> String.valueOf(cb.getBuyerCount()))
                .reduce(String::concat).get());
    }
}