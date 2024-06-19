package beans;

import beans.services.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@EnableAsync
@SpringBootApplication
public class AsyncMainApp {

    public static void main(String[] args) {
        log.info("main method executed by {}", Thread.currentThread());
        ConfigurableApplicationContext context = SpringApplication.run(AsyncMainApp.class, args);
        MyService myService = context.getBean(MyService.class);
        myService.executeAsync();
    }

}
