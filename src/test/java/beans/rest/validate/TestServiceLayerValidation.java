package beans.rest.validate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import template.AbstractTestSpringBootContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestServiceLayerValidation extends AbstractTestSpringBootContext {

    private static final String URL = "/validate/service";

    @Autowired
    MockMvc mvc;

    @Test
    public void testIsValid() throws Exception {
        mvc.perform(put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":3,\"name\":\"vasile\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{id:3,name:\"vasile\"}"));
    }

    @Test
    public void testIdIsNull() throws Exception {
        mvc.perform(put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"vasile\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{invoke.model.id:\"must not be null\"}"));
    }

    @Test
    public void testNameIsNull() throws Exception {
        mvc.perform(put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":3}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{invoke.model.name:\"must not be empty\"}"));
    }

    @Test
    public void testNameIsEmpty() throws Exception {
        mvc.perform(put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":3,\"name\":\"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{invoke.model.name:\"must not be empty\"}"));
    }

}
