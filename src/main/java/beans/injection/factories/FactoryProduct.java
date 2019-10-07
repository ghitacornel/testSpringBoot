package beans.injection.factories;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class FactoryProduct {

    @Autowired
    public FactoryProductDependency factoryProductDependency;

    public boolean postConstruct;

    @PostConstruct
    private void postConstruct() {
        postConstruct = true;
    }
}
