package beans.jms.topic;

import beans.jms.AbstractTestSpringBootContext;
import beans.jms.topic.consumer.JMSConsumer1Topic;
import beans.jms.topic.consumer.JMSConsumer2Topic;
import beans.jms.topic.model.JMSMessageForTopic;
import beans.jms.topic.producer.JMSProducerTopic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestJMSTopic extends AbstractTestSpringBootContext {

    @Autowired
    JMSProducerTopic producerTopic;

    @Autowired
    JMSConsumer1Topic consumer1Topic;

    @Autowired
    JMSConsumer2Topic consumer2Topic;

    @Test
    public void testJMS() throws Exception {
        Assertions.assertNull(consumer1Topic.message);
        Assertions.assertNull(consumer2Topic.message);

        producerTopic.createMessageAndSendItToTheQueueWithTopic();

        // wait a little
        Thread.sleep(100);

        Assertions.assertEquals(new JMSMessageForTopic(3, "payload for queue with topic"), consumer1Topic.message);
        Assertions.assertEquals(new JMSMessageForTopic(3, "payload for queue with topic"), consumer2Topic.message);
    }

}
