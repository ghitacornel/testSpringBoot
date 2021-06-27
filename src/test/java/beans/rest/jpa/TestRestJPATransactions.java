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
                    .andExpect(content().json("[]"));
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
        }

    }

    @Test
    public void testTransaction1() throws Exception {

        mvc.perform(get("/person/transaction1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("Validate 2 Transactions Are Present"));

        String content = Utils.readFile("output/TestRestJPATransactions_testTransaction1.json");
        mvc.perform(get("/person/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(content));

    }

    @Test
    public void testTransaction2() throws Exception {

        mvc.perform(get("/person/transaction2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("Validate 1 Transaction Is Present"));

        String content = Utils.readFile("output/TestRestJPATransactions_testTransaction2.json");
        mvc.perform(get("/person/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(content));

    }

    @Test
    public void testTransaction3() throws Exception {

        mvc.perform(get("/person/transaction3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("Invocation with this. does not honor propagation level"));

        String content = Utils.readFile("output/TestRestJPATransactions_testTransaction3.json");
        mvc.perform(get("/person/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(content));

    }

    @Test
    public void testTransaction4() throws Exception {

        mvc.perform(get("/person/transaction4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("Invocation with SELF does honor propagation level"));

        String content = Utils.readFile("output/TestRestJPATransactions_testTransaction4.json");
        mvc.perform(get("/person/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(content));

    }
}
