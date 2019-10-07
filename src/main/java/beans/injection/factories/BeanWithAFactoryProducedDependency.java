package beans.injection.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanWithAFactoryProducedDependency {

    @Autowired
    private FactoryProduct factoryProduct;

    public FactoryProduct getFactoryProduct() {
        return factoryProduct;
    }
}
