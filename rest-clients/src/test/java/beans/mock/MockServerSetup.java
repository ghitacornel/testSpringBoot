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

import java.time.LocalDate;
import java.time.LocalDateTime;

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
            PersonRequestDto inputModel = new PersonRequestDto(1, "input PATCH", LocalDate.of(2023, 12, 19), LocalDateTime.of(2023, 12, 19, 10, 11, 12));
            PersonResponseDto outputModel = new PersonResponseDto(2, "output PATCH", LocalDate.of(2023, 12, 19), LocalDateTime.of(2023, 12, 19, 10, 11, 12));

            wireMockServer.stubFor(WireMock.patch(UrlPattern.fromOneOf("/externalService", null, null, null))
                    .withRequestBody(WireMock.equalToJson(objectMapper.writeValueAsString(inputModel)))
                    .willReturn(WireMock.okJson(objectMapper.writeValueAsString(outputModel))));
        }

        // mock POST
        {
            PersonRequestDto inputModel = new PersonRequestDto(1, "input POST", LocalDate.of(2023, 12, 19), LocalDateTime.of(2023, 12, 19, 10, 11, 12));
            PersonResponseDto outputModel = new PersonResponseDto(2, "output POST", LocalDate.of(2023, 12, 19), LocalDateTime.of(2023, 12, 19, 10, 11, 12));

            wireMockServer.stubFor(WireMock.post("/externalService")
                    .withRequestBody(WireMock.equalToJson(objectMapper.writeValueAsString(inputModel)))
                    .willReturn(WireMock.okJson(objectMapper.writeValueAsString(outputModel))));
        }

        // mock GET
        {
            PersonResponseDto outputModel = new PersonResponseDto(3, "output GET", LocalDate.of(2023, 12, 19), LocalDateTime.of(2023, 12, 19, 10, 11, 12));

            wireMockServer.stubFor(WireMock.get("/externalService/" + "3?parameter=4")
                    .willReturn(WireMock.okJson(objectMapper.writeValueAsString(outputModel))));
        }

        // mock GET bad data
        {
            PersonResponseDto outputModel = new PersonResponseDto(-3, "", LocalDate.of(2023, 12, 19), LocalDateTime.of(2023, 12, 19, 10, 11, 12));

            wireMockServer.stubFor(WireMock.get("/externalService/badData")
                    .willReturn(WireMock.okJson(objectMapper.writeValueAsString(outputModel))));
        }
    }

}
