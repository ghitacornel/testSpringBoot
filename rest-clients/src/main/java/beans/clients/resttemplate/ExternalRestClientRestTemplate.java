package beans.clients.resttemplate;

import beans.external.ExternalRestServiceInputModel;
import beans.external.ExternalRestServiceOutputModel;
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
        ExternalRestServiceInputModel inputModel = new ExternalRestServiceInputModel();
        inputModel.setInput(input);

        HttpEntity<ExternalRestServiceInputModel> request = new HttpEntity<>(inputModel);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ExternalRestServiceOutputModel> response = restTemplate.postForEntity(url, request, ExternalRestServiceOutputModel.class);
        return Objects.requireNonNull(response.getBody()).getOutput() + " + added by internal client";
    }
}
