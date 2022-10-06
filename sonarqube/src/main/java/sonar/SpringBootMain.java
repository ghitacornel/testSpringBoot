package sonar;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sonar.service.BusinessService;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootMain {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMain.class, args);
    }

    private final BusinessService businessService;

    @PostConstruct
    public void triggerService() {
        System.err.println(businessService.sum(1, 2));
    }
}