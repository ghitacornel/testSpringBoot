package beans.rest.clients.resttemplate;

import beans.rest.clients.resttemplate.model.ExternalRestServiceInputModel;
import beans.rest.clients.resttemplate.model.ExternalRestServiceOutputModel;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ExternalRestClient {

    private final ServerProperties serverProperties;

    public String callExternalService(String input) {
        ExternalRestServiceInputModel inputModel = new ExternalRestServiceInputModel();
        inputModel.setInput(input);

        HttpEntity<ExternalRestServiceInputModel> request = new HttpEntity<>(inputModel);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ExternalRestServiceOutputModel> response = restTemplate.postForEntity("http://localhost:" + serverProperties.getPort() + "/resttemplate", request, ExternalRestServiceOutputModel.class);
        return Objects.requireNonNull(response.getBody()).getOutput();
    }
}
