package beans.rest.versioning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import template.AbstractTestSpringBootContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PathVersioningControllerTest extends AbstractTestSpringBootContext {

    private static final String URL = "/version/path";

    @Autowired
    MockMvc mvc;

    @Test
    public void testVersioning() throws Exception {
        mvc.perform(get(URL + "/v1/invoke"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"name\":\"Bob Charlie\"}"));
        mvc.perform(get(URL + "/v2/invoke"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"firstName\":\"Bob\",\"lastName\":\"Charlie\"}"));
        mvc.perform(get(URL + "/invoke"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"firstName\":\"Bob\",\"lastName\":\"Charlie\"}"));
    }

}
