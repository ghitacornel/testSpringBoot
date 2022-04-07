package beans.rest.clients.resttemplate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import template.AbstractTestSpringBootContext;

public class ExternalRestClientRestTemplateTest extends AbstractTestSpringBootContext {

    @Autowired
    ExternalRestClientRestTemplate client;

    @Test
    public void testClient() {
        Assertions.assertThat(client.callExternalService("input data")).isEqualTo("input data + added by external client + added by internal client");
    }
}
