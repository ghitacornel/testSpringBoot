package beans.clients.feign;

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
        PersonRequestDto inputModel = new PersonRequestDto(1, "input POST");
        PersonResponseDto outputModel = new PersonResponseDto(2, "output POST");

        Assertions.assertThat(client.invokePost(inputModel)).isEqualTo(outputModel);
    }

    @Test
    @SneakyThrows
    public void testGet() {

        PersonResponseDto outputModel = new PersonResponseDto(3, "output GET");

        Assertions.assertThat(client.invokeGet("3")).isEqualTo(outputModel);
    }
}
