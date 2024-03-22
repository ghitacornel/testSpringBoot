package beans.jms.topic.consumer;

import beans.jms.topic.configuration.JMSConfigurationTopic;
import beans.jms.topic.model.MessageForTopic;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
// do not work due to lazy initialisation
//@org.springframework.context.annotation.Scope("prototype")
//@org.springframework.context.annotation.Lazy
@RequiredArgsConstructor
public class JMSConsumer3Topic {

    private final JMSConsumer3TopicRecorder recorder;

    @JmsListener(destination = JMSConfigurationTopic.TOPIC_NAME, containerFactory = "topicConnectionFactory")
    public void listener3ForQueueWithTopic(MessageForTopic message) {
        recorder.message = message;
    }

}
