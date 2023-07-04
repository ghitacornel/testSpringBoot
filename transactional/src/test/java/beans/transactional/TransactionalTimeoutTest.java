package beans.transactional;

import beans.transactional.service.TransactionalService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
    public void timeout_Exceeded() {
        Assertions.assertThrows(Exception.class, () -> {
            mvc.perform(get("/transactional/timeout/15"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("OK"));
        });
    }

    @Test
    public void timeout_Met() throws Exception {
        mvc.perform(get("/transactional/timeout/5"))
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));
    }

}
