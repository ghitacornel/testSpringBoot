package beans.rest.jpa;

import beans.rest.jpa.model.Person;
import beans.rest.jpa.repository.PersonRepository;
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

public class TestRestJPA extends AbstractTestSpringBootContext {

    @Autowired
    MockMvc mvc;

    @Autowired
    PersonRepository personRepository;

    @Before
    public void before() {
        // data for this test only
        Person person = new Person();
        person.setId(2);
        person.setName("gheorghe");
        person.setPassword("db pass gheorghe");
        personRepository.save(person);
        personRepository.flush();

//        final Runnable r = () -> {
//            System.out.println("LAUNCHING HSQL DBMANAGERSWING");
//            final String[] args = { "--url", "jdbc:hsqldb:mem:testdb" ,"--noexit"};
//            try {
//                DatabaseManagerSwing.main(args);
//            } catch (final Exception e) {
//                System.out.println("Could not start hsqldb database manager GUI: " + e.getMessage());
//            }
//        };
//        new Thread(r).start();

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
