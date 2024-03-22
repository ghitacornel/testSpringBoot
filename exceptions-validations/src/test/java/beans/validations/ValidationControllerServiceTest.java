package beans.validations;

import beans.AbstractTestSpringBootContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ValidationControllerServiceTest extends AbstractTestSpringBootContext {

    private static final String URL = "/validate/service";

    @Autowired
    MockMvc mvc;

    @Test
    public void testIsValid() throws Exception {
        mvc.perform(put(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":3,\"name\":\"vasile\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":3,\"name\":\"vasile\"}"));
    }

    @Test
    public void testIdIsNull() throws Exception {
        mvc.perform(put(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"vasile\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("[{\"fieldName\":\"invoke.arg0.id\",\"message\":\"must not be null\",\"messageCode\":\"{jakarta.validation.constraints.NotNull.message}\"}]"));
    }

    @Test
    public void testNameIsNull() throws Exception {
        mvc.perform(put(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":3}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("[{\"fieldName\":\"invoke.arg0.name\",\"message\":\"must not be empty\",\"messageCode\":\"{jakarta.validation.constraints.NotEmpty.message}\"}]"));
    }

    @Test
    public void testNameIsEmpty() throws Exception {
        mvc.perform(put(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":3,\"name\":\"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("[{\"fieldName\":\"invoke.arg0.name\",\"message\":\"must not be empty\",\"messageCode\":\"{jakarta.validation.constraints.NotEmpty.message}\"},{\"fieldName\":\"invoke.arg0.name\",\"message\":\"size must be between 2 and 30\",\"messageCode\":\"{jakarta.validation.constraints.Size.message}\"}]"));
    }

}
