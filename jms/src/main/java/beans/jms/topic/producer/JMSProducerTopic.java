package beans.jms.topic.producer;

import beans.jms.topic.model.JMSMessageForTopic;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import jakarta.jms.Topic;

@Service
@RequiredArgsConstructor
public class JMSProducerTopic {

    private final Topic topic;
    private final JmsTemplate jmsTemplateTopic;

    public void createMessageAndSendItToTheQueueWithTopic() {
        JMSMessageForTopic message = new JMSMessageForTopic(3, "payload for queue with topic");
        jmsTemplateTopic.convertAndSend(topic, message);
    }

}
