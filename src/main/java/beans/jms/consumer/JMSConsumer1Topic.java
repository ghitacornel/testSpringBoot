package beans.jms.consumer;

import beans.jms.model.JMSMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JMSConsumer1Topic {

    public JMSMessage message1FromQueueWithTopic;

    @JmsListener(destination = "spring", containerFactory = "topicConnectionFactory")
    public void listener1ForQueueWithTopic(JMSMessage message) {
        this.message1FromQueueWithTopic = message;
    }


}
