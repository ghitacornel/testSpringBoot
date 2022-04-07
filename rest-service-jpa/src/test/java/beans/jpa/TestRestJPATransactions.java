package beans.jpa;

import beans.jpa.model.Person;
import beans.jpa.repository.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class TestRestJPATransactions {

    @Autowired
    MockMvc mvc;

    @Autowired
    PersonRepository personRepository;

    @BeforeEach
    public void before() {
        // clear all
        try {
            mvc.perform(delete("/person")).andExpect(status().isOk());
            mvc.perform(get("/person/all")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(content().json("[]"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    public void after() {

        // clear all
        try {
            mvc.perform(delete("/person")).andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        {// restore all
            {
                Person person = new Person();
                person.setId(1);
                person.setName("ion");
                person.setPassword("db pass ion");
                personRepository.save(person);
                personRepository.flush();
            }
        }

    }

}
