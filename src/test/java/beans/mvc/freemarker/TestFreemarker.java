package beans.mvc.freemarker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import template.AbstractTestSpringBootContext;
import template.Utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestFreemarker extends AbstractTestSpringBootContext {

    @Autowired
    MockMvc mvc;

    @Test
    public void testController() throws Exception {
        String content = Utils.readFile("output/testMVC.html");
        mvc.perform(get("/freemarker?name=ion"))
                .andExpect(content().xml(content))
                .andExpect(status().isOk());
    }

}
