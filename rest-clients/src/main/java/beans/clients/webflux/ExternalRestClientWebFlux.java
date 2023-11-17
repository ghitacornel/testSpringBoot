package beans.clients.webflux;

import beans.external.PersonRequestDto;
import beans.external.PersonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class ExternalRestClientWebFlux {

    private final WebClient webClient;

    public PersonResponseDto invokePOST(PersonRequestDto inputModel) {
        return webClient
                .post()
                .uri("externalService")
                .bodyValue(inputModel)
                .retrieve()
                .bodyToMono(PersonResponseDto.class)
                .block();
    }

    public PersonResponseDto invokeGET(String path, String query) {
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
