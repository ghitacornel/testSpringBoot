package beans.clients.webclient;

import beans.clients.AbstractTestSpringBootContext;
import beans.clients.webflux.ExternalRestClientWebFlux;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
public class ExternalRestClientWebClientTest extends AbstractTestSpringBootContext {

    @Autowired
    ExternalRestClientWebFlux client;

    @Test
    public void testClient() {
        Assertions.assertThat(client.callExternalService("input data")).isEqualTo("input data + added by external client + added by internal client");
    }
}
