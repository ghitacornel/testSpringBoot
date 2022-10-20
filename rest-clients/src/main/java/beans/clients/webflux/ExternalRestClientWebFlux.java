package beans.clients.webflux;

import beans.external.ExternalRestServiceInputModel;
import beans.external.ExternalRestServiceOutputModel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class ExternalRestClientWebFlux {
    private final ServerProperties serverProperties;

    @Setter
    private String url;

    @PostConstruct
    private void setUpUrl() {
        url = "http://localhost:" + serverProperties.getPort();
    }

    public String callExternalService(String input) {
        ExternalRestServiceInputModel inputModel = new ExternalRestServiceInputModel();
        inputModel.setInput(input);

        WebClient webClient = WebClient.builder()
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        ExternalRestServiceOutputModel outputModel = webClient
                .post()
                .uri("externalService")
                .bodyValue(inputModel)
                .retrieve()
                .bodyToMono(ExternalRestServiceOutputModel.class)
                .block();

        return outputModel.getOutput() + " + added by internal client";
    }
}
