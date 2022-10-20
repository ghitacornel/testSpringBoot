package beans.clients.resttemplate;

import beans.external.RequestDto;
import beans.external.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ExternalRestClientRestTemplate {

    private final ServerProperties serverProperties;
    @Setter
    private String url;

    @PostConstruct
    private void setUpUrl() {
        url = "http://localhost:" + serverProperties.getPort() + "/externalService";
    }

    public String callExternalService(String input) {
        RequestDto inputModel = new RequestDto();
        inputModel.setInput(input);

        HttpEntity<RequestDto> request = new HttpEntity<>(inputModel);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResponseDto> response = restTemplate.postForEntity(url, request, ResponseDto.class);
        return Objects.requireNonNull(response.getBody()).getOutput() + " + added by internal client";
    }
}
