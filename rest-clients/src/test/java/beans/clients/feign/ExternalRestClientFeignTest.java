package beans.clients.feign;

import beans.mock.MockServerSetup;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExternalRestClientFeignTest extends MockServerSetup {

    @Autowired
    ExternalRestClientFeign client;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setUpClientWiremockUrl(WireMockRuntimeInfo wmRuntimeInfo) {
        client.setUrl("http://localhost:" + wmRuntimeInfo.getHttpPort());
    }

    @Test
    @SneakyThrows
    public void testPost() {

        Assertions.assertThat(client.invokePost("input data"))
                .isEqualTo("input data + added by external client + added by internal client");
    }

    @Test
    @SneakyThrows
    public void testGet() {

        Assertions.assertThat(client.invokeGet("XXX"))
                .isEqualTo("input data + added by external client + added by internal client");
    }
}
