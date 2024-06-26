package beans.jms.transactional.producer;

import beans.jms.transactional.model.MessageForTransactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import jakarta.jms.Queue;

@Service
@RequiredArgsConstructor
public class JMSProducerTransactionalQueue {

    @Qualifier("transactionalQueue")
    private final Queue queue;

    @Qualifier("jmsTemplateTransactional")
    private final JmsTemplate jmsTemplate;

    public void createMessageAndSendItToTheTransactionalQueue() {
        MessageForTransactional message = new MessageForTransactional(1, "payload for transactional queue");
        jmsTemplate.convertAndSend(queue, message);
    }

}
