package beans.clients.resttemplate;

import beans.external.RequestDto;
import beans.external.ResponseDto;
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
public class ExternalRestClientRestTemplateTest extends MockServerSetup {

    @Autowired
    ExternalRestClientRestTemplate client;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setUpClientWiremockUrl(WireMockRuntimeInfo wmRuntimeInfo) {
        client.setUrl("http://localhost:" + wmRuntimeInfo.getHttpPort() + "/externalService");
    }

    @Test
    @SneakyThrows
    public void testClient() {

        RequestDto inputModel = new RequestDto();
        inputModel.setInput("input data");
        ResponseDto outputModel = new ResponseDto();
        outputModel.setOutput(inputModel.getInput() + " + added by external client");

        Assertions.assertThat(client.callExternalService("input data"))
                .isEqualTo(outputModel.getOutput() + " + added by internal client");
    }
}
