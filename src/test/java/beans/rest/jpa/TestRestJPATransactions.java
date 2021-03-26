package beans.rest.jpa;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import template.AbstractTestSpringBootContext;
import template.Utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestRestJPATransactions extends AbstractTestSpringBootContext {

    @Autowired
    MockMvc mvc;

    @Before
    public void before() {
        try {
            mvc.perform(delete("/person")).andExpect(status().isOk());
            mvc.perform(get("/person/all")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(content().string("[]"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testTransaction1() throws Exception {
        String content = Utils.readFile("output/TestRestJPATransactions_Validate2TransactionsArePresent.json");

        mvc.perform(get("/person/transaction1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());

        mvc.perform(get("/person/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(content));
    }

}
