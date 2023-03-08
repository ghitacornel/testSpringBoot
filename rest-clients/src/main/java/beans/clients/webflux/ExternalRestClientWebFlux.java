package beans.clients.webflux;

import beans.external.PersonRequestDto;
import beans.external.PersonResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class ExternalRestClientWebFlux {

    @Setter
    private String url;

    public PersonResponseDto invokePOST(PersonRequestDto inputModel) {

        // can use a factory to build
        // can be injected
        WebClient webClient = WebClient.builder()
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return webClient
                .post()
                .uri("externalService")
                .bodyValue(inputModel)
                .retrieve()
                .bodyToMono(PersonResponseDto.class)
                .block();
    }

    public PersonResponseDto invokeGET(String input) {

        // thread safe
        // can use a factory to build
        // can be injected
        WebClient webClient = WebClient.builder()
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return webClient
                .get()
                .uri("externalService" + "/" + input)
                .retrieve()
                .bodyToMono(PersonResponseDto.class)
                .block();
    }
}
