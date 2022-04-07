package beans.jpa;

import beans.Utils;
import beans.jpa.model.Person;
import beans.jpa.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
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
public class TestRestJPA {

    @Autowired
    MockMvc mvc;

    @Autowired
    PersonRepository personRepository;

    @BeforeEach
    public void before() {
        personRepository.deleteAll();
        personRepository.save(new Person(1, "ion", "db pass ion"));
        personRepository.save(new Person(2, "gheorghe", "db pass gheorghe"));
    }

    @Test
    public void testFindAll() throws Exception {
        String content = Utils.readFile("output/TestRestJPA_testFindAll.json");
        mvc.perform(get("/person/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(content));
    }

    @Test
    public void testFindById() throws Exception {
        String content = Utils.readFile("output/TestRestJPA_testFindById.json");
        mvc.perform(get("/person/{id}", "2"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(content));
    }

    @Test
    public void testFindByPassword() throws Exception {
        String content = Utils.readFile("output/TestRestJPA_testFindByPassword.json");
        mvc.perform(get("/person?pass=db pass gheorghe"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(content));
    }

    @Test
    public void testFindByIdWithNoResult() throws Exception {
        mvc.perform(get("/person/{id}", "-1")).andExpect(status().isNotFound());
    }

    @Test
    public void testReadCreateReadUpdateDelete() throws Exception {

        // read
        mvc.perform(get("/person/{id}", "3")).andExpect(status().isNotFound());

        // create
        {
            String content = Utils.readFile("input/TestRestJPA_testReadCreateReadUpdateDelete_CREATE.json");
            mvc.perform(put("/person")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(content))
                    .andExpect(status().isOk())
                    .andExpect(content().string(""));
        }

        // read
        {
            String content = Utils.readFile("output/TestRestJPA_testReadCreateReadUpdateDelete_AFTER_CREATE.json");
            mvc.perform(get("/person/{id}", "3"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(content().json(content));
        }

        // update
        {
            String content = Utils.readFile("input/TestRestJPA_testReadCreateReadUpdateDelete_UPDATE.json");
            mvc.perform(post("/person")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(content))
                    .andExpect(status().isOk())
                    .andExpect(content().string(""));
        }

        // read
        {
            String content = Utils.readFile("output/TestRestJPA_testReadCreateReadUpdateDelete_AFTER_UPDATE.json");
            mvc.perform(get("/person/{id}", "3"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(content().json(content));
        }

        // delete
        mvc.perform(delete("/person/{id}", "3")).andExpect(status().isOk());

        // read
        mvc.perform(get("/person/{id}", "3")).andExpect(status().isNotFound());

    }

}
