package beans.rest.exception;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import template.AbstractTestSpringBootContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BusinessExceptionControllerTest extends AbstractTestSpringBootContext {

    @Autowired
    MockMvc mvc;

    @Test
    public void executeAndRaiseBusinessException() throws Exception {
        mvc.perform(get("/exceptions/executeAndRaiseBusinessException"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("custom business exception message"));
    }

    @Test
    public void executeAndRaiseBusinessExceptionMarked() throws Exception {
        mvc.perform(get("/exceptions/executeAndRaiseBusinessExceptionMarked"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("custom business exception message marked"));
    }
}
