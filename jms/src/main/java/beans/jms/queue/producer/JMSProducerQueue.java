package beans.jms.queue.producer;

import beans.jms.queue.model.JMSMessageQueue;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import jakarta.jms.Queue;

@Service
@RequiredArgsConstructor
public class JMSProducerQueue {

    @Qualifier("queue1")
    private final Queue queue1;

    @Qualifier("queue2")
    private final Queue queue2;

    @Qualifier("jmsTemplateNonTransactional")
    private final JmsTemplate jmsTemplate;

    public void createMessageAndSendItToTheQueue1() {
        JMSMessageQueue message = new JMSMessageQueue(1, "payload for queue 1");
        jmsTemplate.convertAndSend(queue1, message);
    }

    public void createMessageAndSendItToTheQueue2() {
        JMSMessageQueue message = new JMSMessageQueue(2, "payload for queue 2");
        jmsTemplate.convertAndSend(queue2, message);
    }

}
