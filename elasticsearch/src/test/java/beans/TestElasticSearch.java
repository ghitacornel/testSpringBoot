package beans;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestElasticSearch {

    @Autowired
    MockMvc mvc;

    @Test
    public void testSearchParent() throws Exception {
        MvcResult result = mvc.perform(get("/elastic/parent/{content}", "initial dummy data"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        System.err.println(result.getResponse().getContentAsString());
        // observe an SQL select is executed
    }

    @Test
    public void testSearchChild() throws Exception {
        MvcResult result = mvc.perform(get("/elastic/child/{content}", "anna"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        System.err.println(result.getResponse().getContentAsString());
        // observe an SQL select is executed
    }

    @Test
    public void testSearchChildProjection() throws Exception {
        MvcResult result = mvc.perform(get("/elastic/child/projection/{content}", "anna"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        System.err.println(result.getResponse().getContentAsString());
        // observe no SQL select is executed
    }

    @Test
    public void testSearchSimple() throws Exception {
        MvcResult result = mvc.perform(get("/elastic/simple/{content}", "first name"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        System.err.println(result.getResponse().getContentAsString());
        // observe no SQL select is executed
    }
}
