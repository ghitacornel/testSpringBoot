package beans.jms;

import beans.jms.model.JMSMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import template.AbstractTestSpringBootContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestJMS extends AbstractTestSpringBootContext {

    @Autowired
    MockMvc mvc;

    @Autowired
    JmsConsumer jmsConsumer;

    @Test
    public void testJMS() throws Exception {
        Assertions.assertNull(jmsConsumer.message);
        mvc.perform(post("/jms")).andExpect(status().isOk());

        // wait a little
        Thread.sleep(100);

        Assertions.assertNotNull(jmsConsumer.message);
        Assertions.assertEquals(new JMSMessage(1, "payload"), jmsConsumer.message);
    }

}
