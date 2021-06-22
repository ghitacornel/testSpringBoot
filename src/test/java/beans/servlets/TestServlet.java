package beans.servlets;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import template.AbstractTestSpringBootContext;

import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestServlet extends AbstractTestSpringBootContext {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ServletWebServerApplicationContext webServerAppCtxt;

    private final TestRestTemplate testRestTemplate = new TestRestTemplate();

    // http://localhost:8080/customServletURL
    @Test
    public void testServlet() throws Exception {
//        ResponseEntity<String> entity = testRestTemplate.getForEntity("http://localhost:8080/customServletURL", String.class);
//        mvc.perform(get("/customServletURL")
//                .contentType(MediaType.TEXT_HTML))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
//                .andExpect(content().string("aaa"));
    }

}
