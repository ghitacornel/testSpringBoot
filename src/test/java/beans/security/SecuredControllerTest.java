package beans.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import template.AbstractTestSpringBootContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SecuredControllerTest extends AbstractTestSpringBootContext {

    @Autowired
    MockMvc mvc;

    @Test
    public void securedResource() throws Exception {
        mvc.perform(get("/secured"))
                .andExpect(status().isOk())
                .andExpect(content().string("secured answer"));
    }

}
