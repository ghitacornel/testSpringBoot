package beans.jms.transactional.producer;

import beans.jms.transactional.model.JMSMessageTransactionalQueue;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;

@Service
@RequiredArgsConstructor
public class JMSProducerNonTransactionalQueue {

    private final Queue transactionalQueue;
    private final JmsTemplate jmsTemplateNonTransactionalQueue;

    public void createMessageAndSendItToTheTransactionalQueue() {
        JMSMessageTransactionalQueue message = new JMSMessageTransactionalQueue(1, "payload for transactional queue non transactional");
        jmsTemplateNonTransactionalQueue.convertAndSend(transactionalQueue, message);
    }

}