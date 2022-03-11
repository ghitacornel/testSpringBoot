package beans.jms;

import beans.jms.model.JMSMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;

@RestController
@RequestMapping(value = "/jms")
@RequiredArgsConstructor
public class JmsController {

    private final Queue queue;
    private final JmsTemplate jmsTemplate;

    // use this post to produce a message and send it to the queue
    @PostMapping
    public void findById() {
        jmsTemplate.convertAndSend(queue, new JMSMessage(1, "payload"));
    }

}
