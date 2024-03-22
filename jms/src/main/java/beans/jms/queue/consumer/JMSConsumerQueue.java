package beans.jms.queue.consumer;

import beans.jms.queue.configuration.JMSConfigurationQueue;
import beans.jms.queue.model.MessageForQueue;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JMSConsumerQueue {

    // record last message for test purpose
    public MessageForQueue messageFromQueue1;
    // record last message for test purpose
    public MessageForQueue messageFromQueue2;

    @JmsListener(destination = JMSConfigurationQueue.QUEUE_1)
    public void listenerForQueue1(MessageForQueue message) {
        this.messageFromQueue1 = message;
    }

    @JmsListener(destination = JMSConfigurationQueue.QUEUE_2)
    public void listenerForQueue2(MessageForQueue message) {
        this.messageFromQueue2 = message;
    }

}
