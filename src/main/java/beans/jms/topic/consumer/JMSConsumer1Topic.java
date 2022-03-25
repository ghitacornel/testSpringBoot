package beans.jms.topic.consumer;

import beans.jms.topic.configuration.JMSConfigurationTopic;
import beans.jms.topic.model.JMSMessageForTopic;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JMSConsumer1Topic {

    // record last message for test purpose
    public JMSMessageForTopic message;

    @JmsListener(destination = JMSConfigurationTopic.TOPIC_NAME, containerFactory = "topicConnectionFactory")
    public void listener1ForQueueWithTopic(JMSMessageForTopic message) {
        this.message = message;
    }

}
