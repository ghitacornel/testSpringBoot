package beans.properties.environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * observe that there is no need to define specific setters for such kind of injected properties
 */
@Component
public class BeanWithPropertiesInjectedFromEnvironment {

    /**
     * Inject a specific Spring mechanism that allow access to environment variables<br>
     */
    @Autowired
    Environment environment;

    /**
     * note the specific construction : [environment] +[:]+[environment property name]
     */
    @Value("${environment:environmentProperty}")
    private String environmentProperty;

    public Environment getEnvironment() {
        return environment;
    }

    public String getEnvironmentProperty() {
        return environmentProperty;
    }
}
