package beans.jms.topic.producer;

import beans.jms.topic.model.MessageForTopic;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import jakarta.jms.Topic;

@Service
@RequiredArgsConstructor
public class JMSProducerTopic {

    private final Topic topic;

    @Qualifier("jmsTemplateTopic")
    private final JmsTemplate jmsTemplate;

    public void createMessageAndSendItToTheQueueWithTopic() {
        MessageForTopic message = new MessageForTopic(3, "payload for queue with topic");
        jmsTemplate.convertAndSend(topic, message);
    }

}
