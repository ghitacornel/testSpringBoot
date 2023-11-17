package beans.mock;

import beans.external.PersonRequestDto;
import beans.external.PersonResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.matching.UrlPattern;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import static beans.mock.WireMockExtension.wireMockServer;

@ExtendWith(WireMockExtension.class)
public abstract class MockServerSetup {

    @Autowired
    protected ObjectMapper objectMapper;

    @BeforeEach
    @SneakyThrows
    public void setupExternalApplicationAsMock() {

        // mock PATCH
        {
            PersonRequestDto inputModel = new PersonRequestDto(1, "input PATCH");
            PersonResponseDto outputModel = new PersonResponseDto(2, "output PATCH");

            wireMockServer.stubFor(WireMock.patch(UrlPattern.fromOneOf("/externalService", null, null, null))
                    .withRequestBody(WireMock.equalToJson(objectMapper.writeValueAsString(inputModel)))
                    .willReturn(WireMock.okJson(objectMapper.writeValueAsString(outputModel))));
        }

        // mock POST
        {
            PersonRequestDto inputModel = new PersonRequestDto(1, "input POST");
            PersonResponseDto outputModel = new PersonResponseDto(2, "output POST");

            wireMockServer.stubFor(WireMock.post("/externalService")
                    .withRequestBody(WireMock.equalToJson(objectMapper.writeValueAsString(inputModel)))
                    .willReturn(WireMock.okJson(objectMapper.writeValueAsString(outputModel))));
        }

        // mock GET
        {
            PersonResponseDto outputModel = new PersonResponseDto(3, "output GET");

            wireMockServer.stubFor(WireMock.get("/externalService/" + "3?parameter=4")
                    .willReturn(WireMock.okJson(objectMapper.writeValueAsString(outputModel))));
        }

        // mock GET bad data
        {
            PersonResponseDto outputModel = new PersonResponseDto(-3, "");

            wireMockServer.stubFor(WireMock.get("/externalService/badData")
                    .willReturn(WireMock.okJson(objectMapper.writeValueAsString(outputModel))));
        }
    }

}
