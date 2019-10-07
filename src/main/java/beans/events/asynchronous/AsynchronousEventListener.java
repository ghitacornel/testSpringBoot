package beans.events.asynchronous;

import beans.events.CustomSpringEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class AsynchronousEventListener {

    /**
     * Remember to enable asynchronous method execution in your application with @EnableAsync
     */
    @Async
    @EventListener
    public void processEvent(CustomSpringEvent event) {
        processedEvents.add(event);
        processingThreads.add(Thread.currentThread());

        // ensure processing takes longer hence a second thread will process the next message
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException("can't sleep", e);
        }
    }

    final private List<CustomSpringEvent> processedEvents = new ArrayList<>();
    final private Set<Thread> processingThreads = new HashSet<>();

    public List<CustomSpringEvent> getProcessedEvents() {
        return processedEvents;
    }

    public Set<Thread> getProcessingThreads() {
        return processingThreads;
    }
}