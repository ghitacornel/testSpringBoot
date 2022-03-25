package beans.jms.consumer;

import beans.jms.model.JMSMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JMSConsumer2Topic {

    public JMSMessage message2FromQueueWithTopic;

    @JmsListener(destination = "spring", containerFactory = "topicConnectionFactory")
    public void listener2ForQueueWithTopic(JMSMessage message) {
        this.message2FromQueueWithTopic = message;
    }

}
