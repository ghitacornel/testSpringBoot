package beans.jms.transactional.consumer;

import beans.jms.transactional.configuration.JMSConfigurationTransactionalQueue;
import beans.jms.transactional.model.MessageForTransactional;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JMSConsumerTransactionalQueue {

    // record last message for test purpose
    public MessageForTransactional message;

    @JmsListener(destination = JMSConfigurationTransactionalQueue.TRANSACTIONAL_QUEUE)
    public void listenerForQueue1(MessageForTransactional message) {
        this.message = message;
    }

}
