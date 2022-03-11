package beans.jms;

import beans.jms.model.JMSMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;

@Service
@RequiredArgsConstructor
public class JmsProducer {

    private final Queue queue;
    private final JmsTemplate jmsTemplate;

    public void createMessageAndSendItToTheQueue() {
        JMSMessage message = new JMSMessage(1, "payload");
        jmsTemplate.convertAndSend(queue, message);
    }

}
