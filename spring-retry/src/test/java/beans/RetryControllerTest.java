package beans;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RetryControllerTest {

    private static final String STABLE_RESOURCE = "/retry/stableResource";
    private static final String RETRY_RESOURCE_FAIL_BASED_ON_PARAMETER = "/retry/resourceFailBasedOnParameter";
    private static final String RETRY_RESOURCE_FAIL_WITH_NO_BACKUP = "/retry/resourceFailWithNoBackup";

    private static final String FIND_ALL = "/retry/findAll";
    private static final String DELETE_ALL = "/retry/deleteAll";
    private static final String RETRY_TEST_TRANSACTION_IS_NOT_ROLLED_BACK_WHEN_DEFAULT_EXISTS = "/retry/testTransactionIsNotRolledBackWhenDefaultExists";

    @Autowired
    MockMvc mvc;

    @Test
    public void stableResource() throws Exception {
        mvc.perform(get(STABLE_RESOURCE))
                .andExpect(status().isOk())
                .andExpect(content().string("stable resource"));
    }

    @Test
    public void resourceFailBasedOnParameter_OK() throws Exception {
        mvc.perform(get(RETRY_RESOURCE_FAIL_BASED_ON_PARAMETER + "/false"))
                .andExpect(status().isOk())
                .andExpect(content().string("resource with temporary issues"));
    }

    @Test
    public void resourceFailBasedOnParameter_FAIL_TO_DEFAULT() throws Exception {
        mvc.perform(get(RETRY_RESOURCE_FAIL_BASED_ON_PARAMETER + "/true"))
                .andExpect(status().isOk())
                .andExpect(content().string("resource unavailable for now, returning default for parameter=true"));
    }

    @Test
    public void resourceFailWithNoBackup() throws Exception {
        mvc.perform(get(RETRY_RESOURCE_FAIL_WITH_NO_BACKUP))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("resource unavailable and unrecoverable"));
    }

    @Test
    public void testTransactionIsNotRolledBackWhenDefaultExists() throws Exception {
        mvc.perform(get(DELETE_ALL))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
        mvc.perform(get(FIND_ALL))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
        mvc.perform(get(RETRY_TEST_TRANSACTION_IS_NOT_ROLLED_BACK_WHEN_DEFAULT_EXISTS))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
        mvc.perform(get(FIND_ALL))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"dummy\"}]"));
    }
}
