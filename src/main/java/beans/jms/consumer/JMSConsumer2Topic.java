package beans.jms.consumer;

import beans.jms.configuration.JMSConfiguration;
import beans.jms.model.JMSMessageForTopic;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JMSConsumer2Topic {

    // record last message for test purpose
    public JMSMessageForTopic message;

    @JmsListener(destination = JMSConfiguration.TOPIC_NAME, containerFactory = "topicConnectionFactory")
    public void listener2ForQueueWithTopic(JMSMessageForTopic message) {
        this.message = message;
    }

}
