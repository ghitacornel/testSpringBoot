package beans.jms.transactional.consumer;

import beans.jms.transactional.configuration.JMSConfigurationTransactionalQueue;
import beans.jms.transactional.model.JMSMessageTransactionalQueue;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JMSConsumerTransactionalQueue {

    // record last message for test purpose
    public JMSMessageTransactionalQueue message;

    @JmsListener(destination = JMSConfigurationTransactionalQueue.TRANSACTIONAL_QUEUE)
    public void listenerForQueue1(JMSMessageTransactionalQueue message) {
        this.message = message;
    }

}
