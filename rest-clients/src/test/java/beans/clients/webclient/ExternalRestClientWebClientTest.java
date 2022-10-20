package beans.clients.webclient;

import beans.clients.webflux.ExternalRestClientWebFlux;
import beans.external.ExternalRestServiceInputModel;
import beans.external.ExternalRestServiceOutputModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@WireMockTest
@AutoConfigureMockMvc
public class ExternalRestClientWebClientTest {

    @Autowired
    ExternalRestClientWebFlux client;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    public void testClient(WireMockRuntimeInfo wmRuntimeInfo) {

        ExternalRestServiceInputModel inputModel = new ExternalRestServiceInputModel();
        inputModel.setInput("input data");
        ExternalRestServiceOutputModel outputModel = new ExternalRestServiceOutputModel();
        outputModel.setOutput(inputModel.getInput() + " + added by external client");
        WireMock.stubFor(WireMock.post("/externalService")
                .withRequestBody(WireMock.equalToJson(objectMapper.writeValueAsString(inputModel)))
                .willReturn(WireMock.okJson(objectMapper.writeValueAsString(outputModel))));

        client.setUrl("http://localhost:" + wmRuntimeInfo.getHttpPort());

        Assertions.assertThat(client.callExternalService("input data")).isEqualTo("input data + added by external client + added by internal client");
    }
}
