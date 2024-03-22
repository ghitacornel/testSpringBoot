package beans.jms.queue;

import beans.jms.queue.consumer.JMSConsumerQueue;
import beans.jms.queue.model.MessageForQueue;
import beans.jms.queue.producer.JMSProducerQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestJMSQueue {

    @Autowired
    JMSProducerQueue jmsProducer;

    @Autowired
    JMSConsumerQueue jmsConsumer;

    @Test
    public void testJMS() throws Exception {
        Assertions.assertNull(jmsConsumer.messageFromQueue1);
        Assertions.assertNull(jmsConsumer.messageFromQueue2);

        jmsProducer.createMessageAndSendItToTheQueue1();
        jmsProducer.createMessageAndSendItToTheQueue2();

        // wait a little
        Thread.sleep(100);

        Assertions.assertEquals(new MessageForQueue(1, "payload for queue 1"), jmsConsumer.messageFromQueue1);
        Assertions.assertEquals(new MessageForQueue(2, "payload for queue 2"), jmsConsumer.messageFromQueue2);
    }

}
