package testSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientMain {

    // start client second
    // check http://localhost:8082/dummy
    public static void main(String[] args) {
        SpringApplication.run(ClientMain.class, args);
    }
}