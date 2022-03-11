package beans.jms;

import beans.jms.model.JMSMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsConsumer {

    // record last message for test purpose
    public JMSMessage messageFromQueue1;
    // record last message for test purpose
    public JMSMessage messageFromQueue2;

    @JmsListener(destination = "simple-jms-queue-1")
    public void listenerForQueue1(JMSMessage message) {
        this.messageFromQueue1 = message;
    }

    @JmsListener(destination = "simple-jms-queue-2")
    public void listenerForQueue2(JMSMessage message) {
        this.messageFromQueue2 = message;
    }

}
