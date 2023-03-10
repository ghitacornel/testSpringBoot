package beans.clients.resttemplate;

import beans.external.PersonRequestDto;
import beans.external.PersonResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ExternalRestClientRestTemplate {

    // "DEPRECATED"
    // can use a factory to build
    // can be injected
    private final RestTemplate restTemplate = new RestTemplate();

    @Setter
    private String url;

    public PersonResponseDto invokePOST(PersonRequestDto inputModel) {
        HttpEntity<PersonRequestDto> request = new HttpEntity<>(inputModel);
        ResponseEntity<PersonResponseDto> response = restTemplate.postForEntity(url, request, PersonResponseDto.class);
        return response.getBody();
    }

    public PersonResponseDto invokeGET(String path, String query) {
        ResponseEntity<PersonResponseDto> response = restTemplate.getForEntity(url + "/" + path + "?parameter=" + query, PersonResponseDto.class);
        return response.getBody();
    }
}
