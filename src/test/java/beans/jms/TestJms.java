package beans.jms;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import template.AbstractTestSpringBootContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestJms extends AbstractTestSpringBootContext {

    @Autowired
    MockMvc mvc;

    @Autowired
    JmsConsumer jmsConsumer;

    @Test
    public void testJMS() throws Exception {
        Assert.assertNull(jmsConsumer.message);
        mvc.perform(get("/jms")).andExpect(status().isOk());

        // wait a little
        Thread.sleep(100);

        Assert.assertNotNull(jmsConsumer.message);
        System.out.println(jmsConsumer.message);
    }

}
