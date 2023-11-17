package beans.clients.webflux;

import beans.external.PersonRequestDto;
import beans.external.PersonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class ExternalRestClientWebFlux {

    @Value("${webflux.client.url}")
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

    public PersonResponseDto invokeGET(String path, String query) {

        // thread safe
        // can use a factory to build
        // can be injected
        WebClient webClient = WebClient.builder()
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("externalService/{path}")
                        .queryParam("parameter", query)
                        .build(path))
                .retrieve()
                .bodyToMono(PersonResponseDto.class)
                .block();
    }
}
