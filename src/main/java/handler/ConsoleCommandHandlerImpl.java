package handler;

import service.QueueService;

public class ConsoleCommandHandlerImpl implements ConsoleCommandHandler {
    private final QueueService queueService;

    public ConsoleCommandHandlerImpl(QueueService queueService) {
        this.queueService = queueService;
    }

    @Override
    public void handle(String command) {
        switch (command) {
            case "A":
                System.out.println(queueService.addToQueue());
                break;
            default:
                System.out.println(queueService.removeFromQueueIfExists(command));
                break;
        }
//        printAdditionalInfo();
    }

    private void printAdditionalInfo() {
        System.out.println();
        queueService.getAllCashBoxes().forEach(System.out::println);
        System.out.println();
    }
}
