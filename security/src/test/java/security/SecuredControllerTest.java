package security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class SecuredControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void securedResource() throws Exception {
        mvc.perform(get("/secured").with(user("user").password("password")))
                .andExpect(status().isOk())
                .andExpect(content().string("secured answer"));

        mvc.perform(get("/secured").with(user("admin").password("admin")))
                .andExpect(status().isOk())
                .andExpect(content().string("secured answer"));

        mvc.perform(get("/secured").with(anonymous()))
                .andExpect(status().isUnauthorized());

        // this one still works ???
        mvc.perform(get("/secured").with(user("xxx").password("yyy").roles("zzz")))
                .andExpect(status().isOk())
                .andExpect(content().string("secured answer"));
    }

}
