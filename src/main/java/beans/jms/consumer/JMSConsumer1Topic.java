package beans.jms.consumer;

import beans.jms.configuration.JMSConfiguration;
import beans.jms.model.JMSMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JMSConsumer1Topic {

    // record last message for test purpose
    public JMSMessage message1FromQueueWithTopic;

    @JmsListener(destination = JMSConfiguration.TOPIC_NAME, containerFactory = "topicConnectionFactory")
    public void listener1ForQueueWithTopic(JMSMessage message) {
        this.message1FromQueueWithTopic = message;
    }


}
