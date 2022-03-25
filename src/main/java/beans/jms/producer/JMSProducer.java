package beans.jms.producer;

import beans.jms.model.JMSMessage;
import beans.jms.model.JMSMessageForTopic;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;
import javax.jms.Topic;

@Service
@RequiredArgsConstructor
public class JMSProducer {

    private final Queue queue1;
    private final Queue queue2;
    private final JmsTemplate jmsTemplate;

    private final Topic topic;
    private final JmsTemplate jmsTemplateTopic;

    public void createMessageAndSendItToTheQueue1() {
        JMSMessage message = new JMSMessage(1, "payload for queue 1");
        jmsTemplate.convertAndSend(queue1, message);
    }

    public void createMessageAndSendItToTheQueue2() {
        JMSMessage message = new JMSMessage(2, "payload for queue 2");
        jmsTemplate.convertAndSend(queue2, message);
    }

    public void createMessageAndSendItToTheQueueWithTopic() {
        JMSMessageForTopic message = new JMSMessageForTopic(3, "payload for queue with topic");
        jmsTemplateTopic.convertAndSend(topic, message);
    }

}
