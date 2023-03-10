package beans.config;

import beans.service.HelloWorldService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.xml.ws.Endpoint;

@Configuration
@RequiredArgsConstructor
public class EndpointPublishConfig {

    private final HelloWorldService service;

    @PostConstruct
    public void setup() {
        Endpoint.publish("http://localhost:9090/HelloServerPort",
                service);
        // check http://localhost:9090/HelloServerPort?wsdl
    }
}
