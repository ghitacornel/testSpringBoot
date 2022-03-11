package beans.jms;

import beans.jms.consumer.JMSConsumer;
import beans.jms.model.JMSMessage;
import beans.jms.producer.JMSProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import template.AbstractTestSpringBootContext;

public class TestJMS extends AbstractTestSpringBootContext {

    @Autowired
    JMSProducer jmsProducer;

    @Autowired
    JMSConsumer jmsConsumer;

    @Test
    public void testJMS() throws Exception {
        Assertions.assertNull(jmsConsumer.messageFromQueue1);
        Assertions.assertNull(jmsConsumer.messageFromQueue2);
        jmsProducer.createMessageAndSendItToTheQueue1();
        jmsProducer.createMessageAndSendItToTheQueue2();

        // wait a little
        Thread.sleep(100);

        Assertions.assertNotNull(jmsConsumer.messageFromQueue1);
        Assertions.assertNotNull(jmsConsumer.messageFromQueue2);
        Assertions.assertEquals(new JMSMessage(1, "payload for queue 1"), jmsConsumer.messageFromQueue1);
        Assertions.assertEquals(new JMSMessage(2, "payload for queue 2"), jmsConsumer.messageFromQueue2);
    }

}
