package beans.mock;

import beans.external.RequestDto;
import beans.external.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

@WireMockTest
public abstract class MockServerSetup {

    @BeforeEach
    @SneakyThrows
    public void setupExternalApplicationAsMock() {

        {
            RequestDto inputModel = new RequestDto();
            inputModel.setInput("input data");
            ResponseDto outputModel = new ResponseDto();
            outputModel.setOutput(inputModel.getInput() + " + added by external client");

            ObjectMapper objectMapper = new ObjectMapper();

            WireMock.stubFor(WireMock.post("/externalService")
                    .withRequestBody(WireMock.equalToJson(objectMapper.writeValueAsString(inputModel)))
                    .willReturn(WireMock.okJson(objectMapper.writeValueAsString(outputModel))));
        }

        {
            RequestDto inputModel = new RequestDto();
            inputModel.setInput("input data");
            ResponseDto outputModel = new ResponseDto();
            outputModel.setOutput(inputModel.getInput() + " + added by external client");

            WireMock.stubFor(WireMock.get("/externalService/" + "XXX")
                    .willReturn(WireMock.ok(outputModel.getOutput())));
        }
    }
}
