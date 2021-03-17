package service;

import entity.CashBox;

import java.util.List;

public interface QueueService {

    String addToQueue();
    String removeFromQueueIfExists(String queueNumber);
    List<CashBox> getAllCashBoxes();
}
