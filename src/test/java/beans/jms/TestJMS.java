package beans.jms;

import beans.jms.consumer.JMSConsumer;
import beans.jms.consumer.JMSConsumer1Topic;
import beans.jms.consumer.JMSConsumer2Topic;
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

    @Autowired
    JMSConsumer1Topic jmsConsumer1Topic;

    @Autowired
    JMSConsumer2Topic jmsConsumer2Topic;

    @Test
    public void testJMS() throws Exception {
        Assertions.assertNull(jmsConsumer.messageFromQueue1);
        Assertions.assertNull(jmsConsumer.messageFromQueue2);
        Assertions.assertNull(jmsConsumer1Topic.message);
        Assertions.assertNull(jmsConsumer2Topic.message);

        jmsProducer.createMessageAndSendItToTheQueue1();
        jmsProducer.createMessageAndSendItToTheQueue2();
        jmsProducer.createMessageAndSendItToTheQueueWithTopic();

        // wait a little
        Thread.sleep(100);

        Assertions.assertNotNull(jmsConsumer.messageFromQueue1);
        Assertions.assertNotNull(jmsConsumer.messageFromQueue2);
        Assertions.assertNotNull(jmsConsumer1Topic.message);
        Assertions.assertNotNull(jmsConsumer2Topic.message);
        Assertions.assertEquals(new JMSMessage(1, "payload for queue 1"), jmsConsumer.messageFromQueue1);
        Assertions.assertEquals(new JMSMessage(2, "payload for queue 2"), jmsConsumer.messageFromQueue2);
        Assertions.assertEquals(new JMSMessage(3, "payload for queue with topic"), jmsConsumer1Topic.message);
        Assertions.assertEquals(new JMSMessage(3, "payload for queue with topic"), jmsConsumer2Topic.message);
    }

}
