package beans.rest.clients.resttemplate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import template.AbstractTestSpringBootContext;

public class ExternalRestClientTest extends AbstractTestSpringBootContext {

    @Autowired
    ExternalRestClient client;

    @Test
    public void testClient() {
        Assertions.assertThat(client.callExternalService("input data")).isEqualTo("input datainput data");
    }
}
