package beans.clients.feign;

import beans.external.PersonRequestDto;
import beans.external.PersonResponseDto;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class ExternalRestClientFeign {

    @Setter
    private String url;
    private final ServerProperties serverProperties;
    private ExternalServiceContract client;

    @PostConstruct
    private void setUpUrl() {
        url = "http://localhost:" + serverProperties.getPort();


        // thread safe
        // can use a factory to build
        // can be injected
        client = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(ExternalServiceContract.class))
                .logLevel(Logger.Level.FULL)
                .target(ExternalServiceContract.class, url);

    }

    public PersonResponseDto invokeGet(String input) {

        // thread safe
        // can use a factory to build
        // can be injected
        client = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(ExternalServiceContract.class))
                .logLevel(Logger.Level.FULL)
                .target(ExternalServiceContract.class, url);

        return client.invokeGet(input);
    }

    public PersonResponseDto invokePost(PersonRequestDto inputModel) {

        // thread safe
        // can use a factory to build
        // can be injected
        client = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(ExternalServiceContract.class))
                .logLevel(Logger.Level.FULL)
                .target(ExternalServiceContract.class, url);

        return client.invokePost(inputModel);
    }

}
