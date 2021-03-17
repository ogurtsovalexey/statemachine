package handler;

import func.OccupationCalculatorImpl;
import org.junit.Test;
import org.mockito.Mockito;
import service.QueueService;
import service.QueueServiceImpl;

import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;

public class ConsoleCommandHandlerImplTest {

    @Test
    public void testBehavior() {
        QueueService queueService = Mockito.spy(new QueueServiceImpl(1, Collections.EMPTY_LIST, new OccupationCalculatorImpl()));
        ConsoleCommandHandler handler = Mockito.spy(new ConsoleCommandHandlerImpl(queueService));

        Mockito.when(queueService.addToQueue()).thenReturn("Add");
        Mockito.when(queueService.removeFromQueueIfExists(Mockito.any())).thenReturn("Remove");

        handler.handle("A");
        Mockito.verify(queueService, Mockito.atLeast(1)).addToQueue();

        handler.handle("Any other");
        Mockito.verify(queueService, Mockito.atLeast(1)).removeFromQueueIfExists("Any other");
    }
}