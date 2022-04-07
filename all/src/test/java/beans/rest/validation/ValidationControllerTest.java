package beans.rest.validation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import template.AbstractTestSpringBootContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ValidationControllerTest extends AbstractTestSpringBootContext {

    private static final String URL = "/validate/rest";

    @Autowired
    MockMvc mvc;

    @Test
    public void testIsValid() throws Exception {
        mvc.perform(put(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":3,\"name\":\"vasile\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":3,\"name\":\"vasile\"}"));
    }

    @Test
    public void testIdIsNull() throws Exception {
        mvc.perform(put(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"vasile\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("[{\"fieldName\":\"id\",\"message\":\"must not be null\",\"messageCode\":\"NotNull\"}]"));
    }

    @Test
    public void testIdIsNegative() throws Exception {
        mvc.perform(put(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":-3,\"name\":\"vasile\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("[{\"fieldName\":\"id\",\"message\":\"must be greater than 0\",\"messageCode\":\"Positive\"}]"));
    }

    @Test
    public void testNameIsNull() throws Exception {
        mvc.perform(put(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":3}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("[{\"fieldName\":\"name\",\"message\":\"must not be empty\",\"messageCode\":\"NotEmpty\"}]"));
    }

    @Test
    public void testNameIsEmpty() throws Exception {
        mvc.perform(put(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":3,\"name\":\"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("[{\"fieldName\":\"name\",\"message\":\"must not be empty\",\"messageCode\":\"NotEmpty\"},{\"fieldName\":\"name\",\"message\":\"size must be between 2 and 30\",\"messageCode\":\"Size\"}]"));
    }

    @Test
    public void testNameIsBlankButMoreThan30() throws Exception {
        mvc.perform(put(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":3,\"name\":\"                                   \"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("[{\"fieldName\":\"name\",\"message\":\"size must be between 2 and 30\",\"messageCode\":\"Size\"}]"));
    }

    @Test
    public void testIdIsNullAndNameIsEmpty() throws Exception {
        mvc.perform(put(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("[{\"fieldName\":\"id\",\"message\":\"must not be null\",\"messageCode\":\"NotNull\"},{\"fieldName\":\"name\",\"message\":\"must not be empty\",\"messageCode\":\"NotEmpty\"},{\"fieldName\":\"name\",\"message\":\"size must be between 2 and 30\",\"messageCode\":\"Size\"}]"));
    }

}
