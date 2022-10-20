package beans.clients.feign;

import beans.external.ExternalRestServiceInputModel;
import beans.external.ExternalRestServiceOutputModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@WireMockTest
@AutoConfigureMockMvc
public class ExternalRestClientFeignTest {

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

        ExternalRestServiceInputModel inputModel = new ExternalRestServiceInputModel();
        inputModel.setInput("input data");
        ExternalRestServiceOutputModel outputModel = new ExternalRestServiceOutputModel();
        outputModel.setOutput(inputModel.getInput() + " + added by external client");

        WireMock.stubFor(WireMock.post("/externalService")
                .withRequestBody(WireMock.equalToJson(objectMapper.writeValueAsString(inputModel)))
                .willReturn(WireMock.okJson(objectMapper.writeValueAsString(outputModel))));

        Assertions.assertThat(client.invokePost("input data"))
                .isEqualTo("input data + added by external client + added by internal client");
    }

    @Test
    @SneakyThrows
    public void testGet() {

        ExternalRestServiceInputModel inputModel = new ExternalRestServiceInputModel();
        inputModel.setInput("input data");
        ExternalRestServiceOutputModel outputModel = new ExternalRestServiceOutputModel();
        outputModel.setOutput(inputModel.getInput() + " + added by external client");

        WireMock.stubFor(WireMock.get("/externalService/" + "XXX")
                .willReturn(WireMock.ok(outputModel.getOutput())));

        Assertions.assertThat(client.invokeGet("XXX"))
                .isEqualTo("input data + added by external client + added by internal client");
    }
}
