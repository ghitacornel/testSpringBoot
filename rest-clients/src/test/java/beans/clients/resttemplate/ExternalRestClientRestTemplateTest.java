package beans.clients.resttemplate;

import beans.external.PersonRequestDto;
import beans.external.PersonResponseDto;
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
    public void testPOST() {

        PersonRequestDto inputModel = new PersonRequestDto(1, "input POST");
        PersonResponseDto outputModel = new PersonResponseDto(2, "output POST");

        Assertions.assertThat(client.invokePOST(inputModel)).isEqualTo(outputModel);
    }

    @Test
    @SneakyThrows
    public void testGET() {

        PersonResponseDto outputModel = new PersonResponseDto(3, "output GET");

        Assertions.assertThat(client.invokeGET("3", "4")).isEqualTo(outputModel);
    }
}
