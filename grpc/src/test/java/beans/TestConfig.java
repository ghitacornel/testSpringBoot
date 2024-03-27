package beans;

import io.grpc.Server;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
class TestConfig {

    @Autowired
    Server server;

    @PostConstruct
    public void init() throws IOException {
        server.start();
    }

}
