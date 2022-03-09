package beans.rest.validate;

import org.junit.jupiter.api.Test;
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
                .andExpect(content().string("[{\"fieldName\":\"invoke.model.id\",\"message\":\"must not be null\",\"messageCode\":\"{javax.validation.constraints.NotNull.message}\"}]"));
    }

    @Test
    public void testNameIsNull() throws Exception {
        mvc.perform(put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":3}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("[{\"fieldName\":\"invoke.model.name\",\"message\":\"must not be empty\",\"messageCode\":\"{javax.validation.constraints.NotEmpty.message}\"}]"));
    }

    @Test
    public void testNameIsEmpty() throws Exception {
        mvc.perform(put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":3,\"name\":\"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("[{\"fieldName\":\"invoke.model.name\",\"message\":\"must not be empty\",\"messageCode\":\"{javax.validation.constraints.NotEmpty.message}\"},{\"fieldName\":\"invoke.model.name\",\"message\":\"size must be between 2 and 30\",\"messageCode\":\"{javax.validation.constraints.Size.message}\"}]"));
    }

}
