package beans.factories;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class ConfigurationFactoryProductSingleton {

    public String createdBy = "CONSTRUCTOR";

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
