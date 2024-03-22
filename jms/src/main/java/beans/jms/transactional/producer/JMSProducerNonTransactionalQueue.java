package beans.jms.transactional.producer;

import beans.jms.transactional.model.JMSMessageTransactionalQueue;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import jakarta.jms.Queue;

@Service
@RequiredArgsConstructor
public class JMSProducerNonTransactionalQueue {

    @Qualifier("transactionalQueue")
    private final Queue queue;

    @Qualifier("jmsTemplateNonTransactional")
    private final JmsTemplate jmsTemplate;

    public void createMessageAndSendItToTheTransactionalQueue() {
        JMSMessageTransactionalQueue message = new JMSMessageTransactionalQueue(1, "payload for transactional queue non transactional");
        jmsTemplate.convertAndSend(queue, message);
    }

}
