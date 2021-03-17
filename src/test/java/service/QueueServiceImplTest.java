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
import java.util.function.Function;

public class QueueServiceImplTest {

    private Function<Collection<CashBox>, String> getCustomersFunc = cashBoxes -> cashBoxes.stream()
            .sorted(Comparator.comparing(CashBox::getName))
            .map(cb -> String.valueOf(cb.getBuyerCount()))
            .reduce(String::concat).get();

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
        Assert.assertEquals("0001", getCustomersFunc.apply(cashBoxes));

        queueService.addToQueue();
        Assert.assertEquals("0011", getCustomersFunc.apply(cashBoxes));

        queueService.addToQueue();
        Assert.assertEquals("0111", getCustomersFunc.apply(cashBoxes));

        queueService.addToQueue();
        Assert.assertEquals("1111", getCustomersFunc.apply(cashBoxes));

        queueService.addToQueue();
        Assert.assertEquals("1112", getCustomersFunc.apply(cashBoxes));

        queueService.addToQueue();
        Assert.assertEquals("1122", getCustomersFunc.apply(cashBoxes));

        queueService.addToQueue();
        Assert.assertEquals("1222", getCustomersFunc.apply(cashBoxes));

        queueService.addToQueue();
        Assert.assertEquals("1223", getCustomersFunc.apply(cashBoxes));

        queueService.addToQueue();
        Assert.assertEquals("1233", getCustomersFunc.apply(cashBoxes));

        queueService.addToQueue();
        Assert.assertEquals("2233", getCustomersFunc.apply(cashBoxes));

        queueService.addToQueue();
        Assert.assertEquals("2333", getCustomersFunc.apply(cashBoxes));

        queueService.addToQueue();
        Assert.assertEquals("3333", getCustomersFunc.apply(cashBoxes));


        queueService.addToQueue();
        Assert.assertEquals("3333", getCustomersFunc.apply(cashBoxes));
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

        Assert.assertEquals("0000", getCustomersFunc.apply(cashBoxes));

        // Add 3 customers to each cashbox
        for (int i = 0; i < 11; i++) {
            queueService.addToQueue();
        }

        queueService.addToQueue();
        Assert.assertEquals("3333", getCustomersFunc.apply(cashBoxes));


        queueService.removeFromQueueIfExists("1");
        Assert.assertEquals("2333", getCustomersFunc.apply(cashBoxes));

        queueService.removeFromQueueIfExists("4");
        Assert.assertEquals("2332", getCustomersFunc.apply(cashBoxes));

        queueService.removeFromQueueIfExists("4");
        Assert.assertEquals("2331", getCustomersFunc.apply(cashBoxes));

        queueService.removeFromQueueIfExists("4");
        Assert.assertEquals("2330", getCustomersFunc.apply(cashBoxes));

        queueService.removeFromQueueIfExists("4");
        Assert.assertEquals("2330", getCustomersFunc.apply(cashBoxes));
    }
}