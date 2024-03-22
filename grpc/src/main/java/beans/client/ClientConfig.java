package beans.client;

import external.service.ExternalServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ClientConfig {

    @Value("${grpc.host}")
    private String host;

    @Value("${grpc.port}")
    private Integer port;

    @Bean
    ManagedChannel managedChannel() {
        return ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
    }

    @Bean
    ExternalServiceGrpc.ExternalServiceBlockingStub externalServiceStub(ManagedChannel managedChannel) {
        return ExternalServiceGrpc.newBlockingStub(managedChannel);
    }

}
