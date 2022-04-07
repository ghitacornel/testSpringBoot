package beans.clients.feign;

import beans.clients.AbstractTestSpringBootContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ExternalRestClientFeignTest extends AbstractTestSpringBootContext {

    @Autowired
    ExternalRestClientFeign client;

    @Test
    public void testClient() {
        Assertions.assertThat(client.invokePost("input data")).isEqualTo("input data + added by external client + added by internal client");
        Assertions.assertThat(client.invokeGet("input data")).isEqualTo("input data + added by external client + added by internal client");
    }
}