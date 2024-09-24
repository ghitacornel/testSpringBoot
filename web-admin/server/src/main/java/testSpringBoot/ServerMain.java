package testSpringBoot;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class ServerMain {

    // start server first
    // check http://localhost:8081/applications
    public static void main(String[] args) {
        SpringApplication.run(ServerMain.class, args);
    }
}