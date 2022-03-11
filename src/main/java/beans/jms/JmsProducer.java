package beans.jms;

import beans.jms.model.JMSMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;

@Service
@RequiredArgsConstructor
public class JmsProducer {

    private final Queue queue1;
    private final Queue queue2;

    private final JmsTemplate jmsTemplate;

    public void createMessageAndSendItToTheQueue1() {
        JMSMessage message = new JMSMessage(1, "payload for queue 1");
        jmsTemplate.convertAndSend(queue1, message);
    }

    public void createMessageAndSendItToTheQueue2() {
        JMSMessage message = new JMSMessage(2, "payload for queue 2");
        jmsTemplate.convertAndSend(queue2, message);
    }

}
