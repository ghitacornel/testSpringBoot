package beans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class JmsMain {
    public static void main(String[] args) {
        SpringApplication.run(JmsMain.class, args);
    }
}
