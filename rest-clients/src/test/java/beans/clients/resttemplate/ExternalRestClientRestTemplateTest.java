package beans.clients.resttemplate;

import beans.clients.AbstractTestSpringBootContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ExternalRestClientRestTemplateTest extends AbstractTestSpringBootContext {

    @Autowired
    ExternalRestClientRestTemplate client;

    @Test
    public void testClient() {
        Assertions.assertThat(client.callExternalService("input data")).isEqualTo("input data + added by external client + added by internal client");
    }
}
