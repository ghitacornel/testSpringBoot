package beans.clients.feign;

import beans.external.PersonRequestDto;
import beans.external.PersonResponseDto;
import beans.mock.MockServerSetup;
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
    FeignContract contract;

    @BeforeEach
    public void setUpClientWiremockUrl(WireMockRuntimeInfo wmRuntimeInfo) {
        System.err.println(wmRuntimeInfo.getHttpPort());
    }

    @Test
    @SneakyThrows
    public void testPOST() {
        PersonRequestDto inputModel = new PersonRequestDto(1, "input POST");
        PersonResponseDto outputModel = new PersonResponseDto(2, "output POST");

        Assertions.assertThat(contract.invokePOST(inputModel)).isEqualTo(outputModel);
    }

    @Test
    @SneakyThrows
    public void testPATCH() {
        PersonRequestDto inputModel = new PersonRequestDto(1, "input PATCH");
        PersonResponseDto outputModel = new PersonResponseDto(2, "output PATCH");

        Assertions.assertThat(contract.invokePATCH(inputModel)).isEqualTo(outputModel);
    }

    @Test
    @SneakyThrows
    public void testGET() {

        PersonResponseDto outputModel = new PersonResponseDto(3, "output GET");

        Assertions.assertThat(contract.invokeGET("3", "4")).isEqualTo(outputModel);
    }

    @Test
    @SneakyThrows
    public void testGETBadData() {

        PersonResponseDto outputModel = new PersonResponseDto(-3, "");

        Assertions.assertThat(contract.invokeGETBadData()).isEqualTo(outputModel);
    }
}
