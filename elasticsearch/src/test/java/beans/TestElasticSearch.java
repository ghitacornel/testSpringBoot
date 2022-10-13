package beans;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Autowired
    ObjectMapper objectMapper;


    @Test
    public void testSearchParent() throws Exception {


        // read
        {
            MvcResult result = mvc.perform(get("/elastic/parent/{content}", "initial dummy data"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andReturn();
            System.out.println(result.getResponse().getContentAsString());
        }

    }

    @Test
    public void testSearchChild() throws Exception {


        // read
        {
            MvcResult result = mvc.perform(get("/elastic/child/{content}", "an apple for anna"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andReturn();
            System.out.println(result.getResponse().getContentAsString());
        }

    }
}
