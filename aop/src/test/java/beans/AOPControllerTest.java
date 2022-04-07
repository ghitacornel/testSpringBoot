package beans;

import beans.aop.LogExecutionAspect;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AOPControllerTest {

    @Autowired
    MockMvc mvc;

    // need only to verify that only this simple aspect was triggered
    @Autowired
    LogExecutionAspect aspect;

    @Test
    public void method1() throws Exception {
        aspect.getLogs().clear();

        mvc.perform(get("/aop/method1"))
                .andExpect(status().isOk())
                .andExpect(content().string("method1"));

        Assertions.assertThat(aspect.getLogs().toString()).isEqualTo("[String beans.controller.AOPAffectedController.method1() started, String beans.controller.AOPAffectedController.method1() ended]");
    }

    @Test
    public void method2() throws Exception {
        aspect.getLogs().clear();

        mvc.perform(get("/aop/method2"))
                .andExpect(status().isOk())
                .andExpect(content().string("method2"));

        Assertions.assertThat(aspect.getLogs().toString()).isEqualTo("[String beans.controller.AOPAffectedController.method2() started, String beans.controller.AOPAffectedController.method2() ended]");
    }

}
