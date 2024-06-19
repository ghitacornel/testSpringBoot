package beans.services;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyComponent {

    @PostConstruct
    @Async// does not work due to dependency injection lifecycle
    public void init() {
        log.info("init executed by {}", Thread.currentThread());
    }

    @Async
    public void executeAsync() {
        log.info("async method executed by {}", Thread.currentThread());
    }

}
