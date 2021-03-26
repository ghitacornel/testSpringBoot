package beans.rest.jpa;

import beans.rest.jpa.model.Person;
import beans.rest.jpa.repository.PersonRepository;
import org.junit.After;
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

    @Autowired
    PersonRepository personRepository;

    @Before
    public void before() {
        // clear all
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

    @After
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
            {
                Person person = new Person();
                person.setId(2);
                person.setName("gheorghe");
                person.setPassword("db pass gheorghe");
                personRepository.save(person);
                personRepository.flush();
            }
        }

    }

    @Test
    public void testTransaction1() throws Exception {

        mvc.perform(get("/person/transaction1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());

        String content = Utils.readFile("output/TestRestJPATransactions_testTransaction1.json");
        mvc.perform(get("/person/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(content));

    }

}
