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
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExternalRestClientFeign {

    @Setter
    private String url;

    public PersonResponseDto invokeGET(String path, String query) {

        // can use a factory to build
        // can be injected
        ExternalServiceContract client = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(ExternalServiceContract.class))
                .logLevel(Logger.Level.FULL)
                .target(ExternalServiceContract.class, url);

        return client.invokeGET(path, query);
    }

    public PersonResponseDto invokePOST(PersonRequestDto inputModel) {

        // thread safe
        // can use a factory to build
        // can be injected
        ExternalServiceContract client = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(ExternalServiceContract.class))
                .logLevel(Logger.Level.FULL)
                .target(ExternalServiceContract.class, url);

        return client.invokePOST(inputModel);
    }

    public PersonResponseDto invokePATCH(PersonRequestDto inputModel) {

        // thread safe
        // can use a factory to build
        // can be injected
        ExternalServiceContract client = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(ExternalServiceContract.class))
                .logLevel(Logger.Level.FULL)
                .target(ExternalServiceContract.class, url);

        return client.invokePATCH(inputModel);
    }

    public PersonResponseDto invokeGETBadData() {
        ExternalServiceContract client = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(ExternalServiceContract.class))
                .logLevel(Logger.Level.FULL)
                .target(ExternalServiceContract.class, url);

        return client.invokeGETBadData();
    }
}
