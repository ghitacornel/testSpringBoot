package beans.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@ConfigurationProperties(prefix = "customprefix")
public class MySpecialConfiguration {

    private String name;
    private String environment;
    private boolean enabled;
    private final List<String> servers = new ArrayList<>();
    private final Map<String, Integer> props = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getServers() {
        return servers;
    }

    public Map<String, Integer> getProps() {
        return props;
    }

    @Override
    public String toString() {
        return "MySpecialConfiguration{" +
                "name='" + name + '\'' +
                ", environment='" + environment + '\'' +
                ", enabled=" + enabled +
                ", servers=" + servers +
                ", props=" + props +
                '}';
    }
}
