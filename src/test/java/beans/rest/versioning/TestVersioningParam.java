package beans.rest.versioning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import template.AbstractTestSpringBootContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestVersioningParam extends AbstractTestSpringBootContext {

    private static final String URL = "/version/param";

    @Autowired
    MockMvc mvc;

    @Test
    public void testVersioning() throws Exception {
        mvc.perform(get(URL + "/student?version=1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\":\"Bob Charlie\"}"));
        mvc.perform(get(URL + "/student?version=2"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"firstName\":\"Bob\",\"lastName\":\"Charlie\"}"));
    }

}
