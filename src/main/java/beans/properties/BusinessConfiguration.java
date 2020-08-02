package beans.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "business.service")
@PropertySource("classpath:custom.properties")
public class BusinessConfiguration {

    private String setting;

    // MANDATORY getters and setters

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public String getSetting() {
        return setting;
    }
}
