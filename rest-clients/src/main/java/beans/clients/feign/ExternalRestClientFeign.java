package beans.clients.feign;

import beans.external.ExternalRestServiceInputModel;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExternalRestClientFeign {

    private final ServerProperties serverProperties;

    public String invokeGet(String input) {
        ExternalServiceContract client = Feign.builder()
                .logger(new Slf4jLogger(ExternalServiceContract.class))
                .logLevel(Logger.Level.FULL)
                .target(ExternalServiceContract.class, "http://localhost:" + serverProperties.getPort());
        return client.invokeGet(input) + " + added by internal client";
    }

    public String invokePost(String input) {
        ExternalRestServiceInputModel inputModel = new ExternalRestServiceInputModel();
        inputModel.setInput(input);

        ExternalServiceContract client = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(ExternalServiceContract.class))
                .logLevel(Logger.Level.FULL)
                .target(ExternalServiceContract.class, "http://localhost:" + serverProperties.getPort());

        return client.invokePost(inputModel).getOutput() + " + added by internal client";
    }

}
