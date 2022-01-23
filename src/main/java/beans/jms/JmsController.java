package beans.jms;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;

@RestController
@RequestMapping(value = "/jms")
@RequiredArgsConstructor
public class JmsController {

    private final Queue queue;
    private final JmsTemplate jmsTemplate;

    @GetMapping
    public void findById() {
        jmsTemplate.convertAndSend(queue, new JMSMessage(1, "payload"));
    }
}
