package beans.jms.consumer;

import beans.jms.configuration.JMSConfiguration;
import beans.jms.model.JMSMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JMSConsumer {

    // record last message for test purpose
    public JMSMessage messageFromQueue1;
    // record last message for test purpose
    public JMSMessage messageFromQueue2;

    @JmsListener(destination = JMSConfiguration.QUEUE_1)
    public void listenerForQueue1(JMSMessage message) {
        this.messageFromQueue1 = message;
    }

    @JmsListener(destination = JMSConfiguration.QUEUE_2)
    public void listenerForQueue2(JMSMessage message) {
        this.messageFromQueue2 = message;
    }

}
