package beans.jms.topic.consumer;

import beans.jms.topic.configuration.JMSConfigurationTopic;
import beans.jms.topic.model.MessageForTopic;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JMSConsumer2Topic {

    // record last message for test purpose
    public MessageForTopic message;

    @JmsListener(destination = JMSConfigurationTopic.TOPIC_NAME, containerFactory = "topicConnectionFactory")
    public void listener2ForQueueWithTopic(MessageForTopic message) {
        this.message = message;
    }

}
