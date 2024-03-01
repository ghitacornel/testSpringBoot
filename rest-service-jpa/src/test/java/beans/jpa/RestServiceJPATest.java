package beans.jpa;

import beans.Utils;
import beans.jpa.model.Person;
import beans.jpa.repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class RestServiceJPATest {

    @Autowired
    MockMvc mvc;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ObjectMapper objectMapper;

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
        mvc.perform(get("/person/{id}", "-1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No value present"));
    }

    @Test
    public void testReadCreateReadUpdateDelete() throws Exception {

        // read
        mvc.perform(get("/person/{id}", "3"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No value present"));

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
        mvc.perform(get("/person/{id}", "3"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No value present"));

    }

    @Test
    public void testReadCreateReadUpdateDelete_Date() throws Exception {
        personRepository.deleteAll();

        Person person = new Person(1, "testForDate", "pass");

        person.setDateOfBirth(LocalDateTime.of(2022, 10, 11, 12, 13, 14));

        // read
        mvc.perform(get("/person/{id}", person.getId()))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No value present"));

        // create
        String personJson = objectMapper.writeValueAsString(person);
        {
            mvc.perform(put("/person")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(personJson))
                    .andExpect(status().isOk())
                    .andExpect(content().string(""));
        }

        System.err.println("json = " + personJson);
        System.err.println("entity = " + personRepository.findById(person.getId()).get());

        // read
        {
            mvc.perform(get("/person/{id}", person.getId()))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(content().json(personJson));
        }

    }
}
