package beans.lifecycle.singleton;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class SingletonWithLifeCycleBean {

    public boolean isPostConstructCalled = false;
    public boolean isPreDestroyCalled = false;

    @PostConstruct
    private void postConstruct() {
        isPostConstructCalled = true;
    }

    @PreDestroy
    private void preDestroy() {
        isPreDestroyCalled = true;
    }

}
