package yaml.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@ConfigurationProperties(prefix = "customprefix")
@Data
public class MySpecialConfiguration {

    private String name;
    private String environment;
    private boolean enabled;
    private final List<String> servers = new ArrayList<>();
    private final Map<String, Integer> props = new HashMap<>();

}
