package beans.mock;

import beans.external.PersonRequestDto;
import beans.external.PersonResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

@WireMockTest
public abstract class MockServerSetup {

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    @SneakyThrows
    public void setupExternalApplicationAsMock() {

        // mock POST
        {
            PersonRequestDto inputModel = new PersonRequestDto(1, "input POST");
            PersonResponseDto outputModel = new PersonResponseDto(2, "output POST");

            WireMock.stubFor(WireMock.post("/externalService")
                    .withRequestBody(WireMock.equalToJson(objectMapper.writeValueAsString(inputModel)))
                    .willReturn(WireMock.okJson(objectMapper.writeValueAsString(outputModel))));
        }

        // mock GET
        {
            PersonResponseDto outputModel = new PersonResponseDto(3, "output GET");

            WireMock.stubFor(WireMock.get("/externalService/" + "3")
                    .willReturn(WireMock.okJson(objectMapper.writeValueAsString(outputModel))));
        }
    }
}
