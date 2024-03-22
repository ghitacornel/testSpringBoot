package beans;

import io.grpc.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GrpcMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(GrpcMain.class, args);

        // start the grpc server
        Server server = applicationContext.getBean(Server.class);
        try {
            server.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}