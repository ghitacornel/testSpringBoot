package beans.jms;

import beans.jms.model.JMSMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import template.AbstractTestSpringBootContext;

public class TestJMS extends AbstractTestSpringBootContext {

    @Autowired
    JmsProducer jmsProducer;

    @Autowired
    JmsConsumer jmsConsumer;

    @Test
    public void testJMS() throws Exception {
        Assertions.assertNull(jmsConsumer.message);
        jmsProducer.createMessageAndSendItToTheQueue();

        // wait a little
        Thread.sleep(100);

        Assertions.assertNotNull(jmsConsumer.message);
        Assertions.assertEquals(new JMSMessage(1, "payload"), jmsConsumer.message);
    }

}
