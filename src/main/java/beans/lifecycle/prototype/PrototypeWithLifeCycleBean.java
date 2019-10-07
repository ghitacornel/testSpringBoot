package beans.lifecycle.prototype;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Scope("prototype")
public class PrototypeWithLifeCycleBean {

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
