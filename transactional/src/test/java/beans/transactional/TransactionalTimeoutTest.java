package beans.transactional;

import beans.transactional.service.TransactionalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionalTimeoutTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    TransactionalService service;

    @Test
    @Timeout(6)// works
    public void timeout_OK() throws Exception {
        mvc.perform(get("/transactional/timeout"))
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));
    }

    @Test
    @Timeout(5)// expected to fail
    public void timeout_Exceeds() throws Exception {
        mvc.perform(get("/transactional/timeout"))
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));
    }

}
