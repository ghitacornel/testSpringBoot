package beans.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsConsumer {

    public JMSMessage message;

    @JmsListener(destination = "simple-jms-queue")
    public void listener(JMSMessage message) {
        this.message = message;
    }
}
