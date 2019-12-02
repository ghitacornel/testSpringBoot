package beans.rest.jpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import template.AbstractTestSpringBootContext;
import template.Utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
public class TestRestJPA extends AbstractTestSpringBootContext {

    @Autowired
    MockMvc mvc;

    @Test
    public void testGetAll() throws Exception {
        String content = Utils.readFile("testREST_All.json");
        mvc.perform(get("/user/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(content));
    }

    @Test
    public void testFindById() throws Exception {
        mvc.perform(get("/user")
                .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{id:1,name:\"ion\",password:\"db pass ion\"}"));
    }

    @Test
    public void testFindByIdWithNoResult() throws Exception {
        mvc.perform(get("/user").param("id", "-1")).andExpect(status().isNotFound());
    }

    @Test
    public void testReadCreateReadUpdateDelete() throws Exception {

        // read
        mvc.perform(get("/user").param("id", "3")).andExpect(status().isNotFound());

        // create
        mvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":3,\"name\":\"vasile\",\"password\":\"db pass vasile\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        // read
        mvc.perform(get("/user")
                .param("id", "3"))
                .andExpect(status().isOk())
                .andExpect(content().json("{id:3,name:\"vasile\",password:\"db pass vasile\"}"));

        // update
        mvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":3,\"name\":\"vasile 1\",\"password\":\"db pass vasile 1\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        // read
        mvc.perform(get("/user")
                .param("id", "3"))
                .andExpect(status().isOk())
                .andExpect(content().json("{id:3,name:\"vasile 1\",password:\"db pass vasile 1\"}"));

        // delete
        mvc.perform(delete("/user/{id}", "3")).andExpect(status().isOk());

        // read
        mvc.perform(get("/user").param("id", "3")).andExpect(status().isNotFound());

    }

}
