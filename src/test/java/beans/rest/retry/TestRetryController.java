package beans.rest.retry;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import template.AbstractTestSpringBootContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestRetryController extends AbstractTestSpringBootContext {

    private static final String STABLE_RESOURCE = "/retry/stableResource";
    private static final String RETRY_RESOURCE_FAIL_BASED_ON_PARAMETER = "/retry/resourceFailBasedOnParameter";
    private static final String RETRY_RESOURCE_FAIL_WITH_NO_BACKUP = "/retry/resourceFailWithNoBackup";

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
}
