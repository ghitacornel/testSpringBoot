package beans.events.synchronous;

import beans.events.EventPublisher;
import org.junit.Assert;
import org.junit.Test;
import template.AbstractTestSpringContext;

public class TestEvents extends AbstractTestSpringContext {

    @Test
    public void testSynchronousProducerConsumer() {

        EventPublisher publisher = context.getBean(EventPublisher.class);
        Assert.assertNotNull(publisher);
        publisher.publishEvent("event message 1");
        publisher.publishEvent("event message 2");

        SynchronousEventListener listener = context.getBean(SynchronousEventListener.class);
        Assert.assertNotNull(listener);
        Assert.assertEquals(2, listener.getProcessedEvents().size());
        Assert.assertEquals("event message 1", listener.getProcessedEvents().get(0).getMessage());
        Assert.assertEquals(publisher, listener.getProcessedEvents().get(0).getSource());
        Assert.assertEquals("event message 2", listener.getProcessedEvents().get(1).getMessage());
        Assert.assertEquals(publisher, listener.getProcessedEvents().get(1).getSource());

    }

}
