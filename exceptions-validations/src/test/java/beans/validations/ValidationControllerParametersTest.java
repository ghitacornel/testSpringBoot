package beans.validations;

import beans.AbstractTestSpringBootContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ValidationControllerParametersTest extends AbstractTestSpringBootContext {

    private static final String URL = "/validateParameters";

    @Autowired
    MockMvc mvc;

    @Test
    public void testIsValid() throws Exception {
        mvc.perform(get(URL + "/6"))
                .andExpect(status().isOk())
                .andExpect(content().string("6"));
    }

    @Test
    public void testIsInvalid() throws Exception {
        mvc.perform(get(URL + "/4"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("[{\"fieldName\":\"invokeDirect.x\",\"message\":\"must be greater than or equal to 5\",\"messageCode\":\"{javax.validation.constraints.Min.message}\"}]"));
    }

}
