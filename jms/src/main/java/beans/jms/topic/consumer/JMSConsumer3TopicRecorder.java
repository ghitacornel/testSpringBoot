package beans.jms.topic.consumer;

import beans.jms.topic.model.MessageForTopic;
import org.springframework.stereotype.Component;

@Component
public class JMSConsumer3TopicRecorder {

    // record last message for test purpose
    public MessageForTopic message;

}
