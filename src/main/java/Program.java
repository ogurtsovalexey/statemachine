import factory.CashBoxFactory;
import func.OccupationCalculatorImpl;
import handler.ConsoleCommandHandler;
import handler.ConsoleCommandHandlerImpl;
import service.QueueServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {
    private static final int CASH_BOX_CAPACITY = 20;
    private final ConsoleCommandHandler commandHandler;

    Program() {
        CashBoxFactory cashBoxFactory = new CashBoxFactory();
        Map<String, Integer> cashBoxesParam = new HashMap<>();
        cashBoxesParam.put("1", 10);
        cashBoxesParam.put("2", 13);
        cashBoxesParam.put("3", 15);
        cashBoxesParam.put("4", 17);

        QueueServiceImpl queueService = new QueueServiceImpl(
                CASH_BOX_CAPACITY,
                cashBoxFactory.createCashBoxes(cashBoxesParam),
                new OccupationCalculatorImpl()
        );
        this.commandHandler = new ConsoleCommandHandlerImpl(queueService);
    }

    void run() {
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.print("Type command please (0 - exit):");
            String command = in.next();

            if (command.equals("0")) {
                return;
            }

            commandHandler.handle(command);
        }
    }
}
