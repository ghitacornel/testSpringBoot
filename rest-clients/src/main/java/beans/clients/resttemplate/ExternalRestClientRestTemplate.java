package beans.clients.resttemplate;

import beans.external.PersonRequestDto;
import beans.external.PersonResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class ExternalRestClientRestTemplate {

    // thread safe
    // can use a factory to build
    // can be injected
    private final RestTemplate restTemplate = new RestTemplate();

    @Setter
    private String url;
    private final ServerProperties serverProperties;

    @PostConstruct
    private void setUpUrl() {
        url = "http://localhost:" + serverProperties.getPort() + "/externalService";
    }

    public PersonResponseDto invokePOST(PersonRequestDto inputModel) {
        HttpEntity<PersonRequestDto> request = new HttpEntity<>(inputModel);
        ResponseEntity<PersonResponseDto> response = restTemplate.postForEntity(url, request, PersonResponseDto.class);
        return response.getBody();
    }

    public PersonResponseDto invokeGET(String input) {
        ResponseEntity<PersonResponseDto> response = restTemplate.getForEntity(url + "/" + input, PersonResponseDto.class);
        return response.getBody();
    }
}
