package beans.mock;

import beans.clients.external.PersonResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@WireMockTest(httpPort = 1111)
public abstract class MockServerSetup {

    @Autowired
    protected ObjectMapper objectMapper;

    @BeforeEach
    @SneakyThrows
    public void setupExternalApplicationAsMock() {

        // mock GET
        {
            PersonResponseDto outputModel = new PersonResponseDto(3, "output GET", LocalDate.of(2023, 12, 19), LocalDateTime.of(2023, 12, 19, 10, 11, 12));

            stubFor(get("/externalService/" + "3?parameter=4")
                    .willReturn(okJson(objectMapper.writeValueAsString(outputModel))));
        }

        // mock GET bad data
        {
            PersonResponseDto outputModel = new PersonResponseDto(-3, "", LocalDate.of(2023, 12, 19), LocalDateTime.of(2023, 12, 19, 10, 11, 12));

            stubFor(get("/externalService/badData")
                    .willReturn(okJson(objectMapper.writeValueAsString(outputModel))));
        }

        // mock GET no data found
        {
            stubFor(get("/externalService/" + "1111?parameter=1111")
                    .willReturn(notFound()));
        }
    }

}
