package beans.mock;

import beans.clients.model.ClientResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@WireMockTest(httpPort = 1111)
public abstract class MockServerSetup {

    @Autowired
    protected ObjectMapper objectMapper;

    @BeforeEach
    @SneakyThrows
    public void setupExternalApplicationAsMock() {
        stubFor(get("/externalService/OK").willReturn(okJson(objectMapper.writeValueAsString(new ClientResponseDto("OK GET dummy content")))));
    }

}
