package beans.factories;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ConfigurationFactory {

    @Bean
    @Scope("prototype")
    public ConfigurationFactoryProductPrototype producePrototype() {
        ConfigurationFactoryProductPrototype product = new ConfigurationFactoryProductPrototype();
        product.createdBy = "producePrototype";
        return product;
    }

    @Bean
    @Scope("singleton")// this is redundant
    public ConfigurationFactoryProductSingleton produceSingleton() {
        ConfigurationFactoryProductSingleton product = new ConfigurationFactoryProductSingleton();
        product.createdBy = "produceSingleton";
        return product;
    }

}
