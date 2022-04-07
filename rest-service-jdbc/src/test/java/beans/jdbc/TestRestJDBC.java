package beans.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TestRestJDBC {

    @Autowired
    MockMvc mvc;

    @Test
    public void testFindAll() throws Exception {
        String content = Utils.readFile("output/TestRestJDBC_testFindAll.json");
        mvc.perform(get("/user/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(content));
    }

    @Test
    public void testFindById() throws Exception {
        String content = Utils.readFile("output/TestRestJDBC_testFindById.json");
        mvc.perform(get("/user")
                .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(content));
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
        {
            String content = Utils.readFile("input/TestRestJDBC_testReadCreateReadUpdateDelete_CREATE.json");
            mvc.perform(put("/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(content))
                    .andExpect(status().isOk())
                    .andExpect(content().string(""));
        }

        // read
        {
            String content = Utils.readFile("output/TestRestJDBC_testReadCreateReadUpdateDelete_AFTER_CREATE.json");
            mvc.perform(get("/user")
                    .param("id", "3"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(content().json(content));
        }

        // update
        {
            String content = Utils.readFile("input/TestRestJDBC_testReadCreateReadUpdateDelete_UPDATE.json");
            mvc.perform(post("/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(content))
                    .andExpect(status().isOk())
                    .andExpect(content().string(""));
        }

        // read
        {
            String content = Utils.readFile("output/TestRestJDBC_testReadCreateReadUpdateDelete_AFTER_UPDATE.json");
            mvc.perform(get("/user")
                    .param("id", "3"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(content));
        }

        // delete
        mvc.perform(delete("/user/{id}", "3")).andExpect(status().isOk());

        // read
        mvc.perform(get("/user").param("id", "3")).andExpect(status().isNotFound());

    }

}
