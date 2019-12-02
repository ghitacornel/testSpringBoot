package beans.rest;

import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import template.AbstractTestSpringBootContext;
import template.Utils;

import javax.annotation.PostConstruct;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
public class TestREST extends AbstractTestSpringBootContext {

    @Autowired
    MockMvc mvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void before() {
        jdbcTemplate.execute("insert into user values (1,'ion','db pass ion');");
        jdbcTemplate.execute("insert into user values (2,'gheorghe','db pass gheorghe');");
    }

    @Test
    public void testGetAll() throws Exception {
        String content = Utils.readFile("testREST_All.json");
        mvc.perform(get("/user/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(content));
    }

    @Test
    public void testFindById() throws Exception {
        String content = Utils.readFile("testREST_Parameter.json");
        mvc.perform(get("/user")
                .param("id", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(content));
    }

    @Test
    public void testFindByIdWithNoResult() throws Exception {
        mvc.perform(get("/user").
                param("id", "-1"))
                .andExpect(status().is4xxClientError());
    }

}
