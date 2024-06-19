package beans.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyService {

    private final MyComponent myComponent;

    @PostConstruct
    public void init() {
        log.info("init executed by {}", Thread.currentThread());
    }

    @Async
    public void executeAsync() {
        log.info("async method executed by {}", Thread.currentThread());
        myComponent.executeAsync();
    }
}
