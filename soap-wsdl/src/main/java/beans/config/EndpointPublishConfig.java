package beans.config;

import beans.service.HelloWorldService;
import lombok.RequiredArgsConstructor;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
@RequiredArgsConstructor
public class EndpointPublishConfig {

    private final HelloWorldService service;

    private final Bus bus;

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, service);
        endpoint.publish("/Hello");
        return endpoint;
    }
//    http://localhost:8080/services/Hello?wsdl
}
