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
    public void resourceFailBasedOnParameter_FAIL() throws Exception {
        mvc.perform(get(RETRY_RESOURCE_FAIL_BASED_ON_PARAMETER + "/true"))
                .andExpect(status().isOk())
                .andExpect(content().string("resource unavailable for now, returning default for parameter=true"));
    }

}
