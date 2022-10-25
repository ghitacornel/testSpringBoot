package beans.exceptions;

import beans.AbstractTestSpringBootContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BusinessExceptionControllerTest extends AbstractTestSpringBootContext {

    @Autowired
    MockMvc mvc;

    @Test
    public void executeAndRaiseBusinessException() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/exceptions/executeAndRaiseBusinessException"))
                .andExpect(status().isBadRequest())
                .andReturn();
        Assertions.assertThat(mvcResult.getResolvedException().getMessage()).isEqualTo("custom business exception message");
        Assertions.assertThat(mvcResult.getResponse().getHeader("custom-response-header")).isEqualTo("custom response header value");
    }

    @Test
    public void executeAndRaiseBusinessExceptionMarked() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/exceptions/executeAndRaiseBusinessExceptionMarked"))
                .andExpect(status().isNotFound())
                .andReturn();
        Assertions.assertThat(mvcResult.getResolvedException().getMessage()).isEqualTo("custom business exception message marked");
        Assertions.assertThat(mvcResult.getResponse().getHeader("custom-response-header")).isEqualTo("custom response header value");
    }
}
