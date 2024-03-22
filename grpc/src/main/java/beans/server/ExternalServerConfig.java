package beans.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
class ExternalServerConfig {

    @Value("${grpc.port}")
    private Integer port;

    @Bean
    Server server(ExternalServiceServer externalServiceServer) {
        return ServerBuilder
                .forPort(port)
                .addService(externalServiceServer)
                .build();
    }

}
