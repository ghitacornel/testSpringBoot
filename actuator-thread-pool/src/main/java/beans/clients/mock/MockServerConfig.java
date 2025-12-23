package beans.clients.mock;

import beans.clients.model.ClientResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@Profile("!test")
@Configuration
class MockServerConfig {

    @Autowired
    protected ObjectMapper objectMapper;

    private WireMockServer wireMockServer;

    @PostConstruct
    @SneakyThrows
    void init() {
        wireMockServer = new WireMockServer(1111);
        wireMockServer.start();

        wireMockServer.stubFor(get("/externalService/ok").willReturn(okJson(objectMapper.writeValueAsString(new ClientResponseDto("ok GET dummy content from 3rd pary client" + Thread.currentThread())))));
        wireMockServer.stubFor(get("/externalService/delay").willReturn(okJson(objectMapper.writeValueAsString(new ClientResponseDto("ok GET dummy content with delay from 3rd pary client" + Thread.currentThread())))
                .withFixedDelay(10000)));
    }

    @PreDestroy
    @SneakyThrows
    void destroy() {
        wireMockServer.stop();
    }
}
