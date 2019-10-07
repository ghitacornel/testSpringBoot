package beans.properties.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * observe that there is no need to define specific setters for such kind of injected properties
 */
@Component
public class BeanWithPropertiesInjectedFromPropertyFiles {

    @Value("${my.value}")
    private String customValue;

    @Value("${my.second.value}")
    private String customSecondValue;

    /**
     * Observe such properties are injected before post construct methods are executed
     */
    @PostConstruct
    private void postConstruct() {
        if (customValue == null) {
            throw new RuntimeException("[customValue] property is not set ");
        }
        if (customSecondValue == null) {
            throw new RuntimeException("[customSecondValue] property is not set ");
        }
    }

    public String getCustomValue() {
        return customValue;
    }

    public String getCustomSecondValue() {
        return customSecondValue;
    }
}
