package beans.rest.jpa;

import beans.rest.jpa.model.Person;
import beans.rest.jpa.repository.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import template.AbstractTestSpringBootContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestRestJPATransactions extends AbstractTestSpringBootContext {

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
