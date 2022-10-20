package beans.clients.webclient;

import beans.clients.webflux.ExternalRestClientWebFlux;
import beans.external.RequestDto;
import beans.external.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@WireMockTest
public class ExternalRestClientWebFluxTest {

    @Autowired
    ExternalRestClientWebFlux client;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setUpClientWiremockUrl(WireMockRuntimeInfo wmRuntimeInfo) {
        client.setUrl("http://localhost:" + wmRuntimeInfo.getHttpPort());
    }

    @Test
    @SneakyThrows
    public void testClient() {

        RequestDto inputModel = new RequestDto();
        inputModel.setInput("input data");
        ResponseDto outputModel = new ResponseDto();
        outputModel.setOutput(inputModel.getInput() + " + added by external client");

        WireMock.stubFor(WireMock.post("/externalService")
                .withRequestBody(WireMock.equalToJson(objectMapper.writeValueAsString(inputModel)))
                .willReturn(WireMock.okJson(objectMapper.writeValueAsString(outputModel))));

        Assertions.assertThat(client.callExternalService("input data"))
                .isEqualTo("input data + added by external client + added by internal client");
    }
}
