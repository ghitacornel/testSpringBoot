package yaml.configurations;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
@ConfigurationProperties(prefix = "customprefix")
@Getter
@Setter
@ToString
public class MySpecialConfiguration {

    private String name;
    private String environment;
    private boolean enabled;
    private final List<String> servers = new ArrayList<>();
    private final Map<String, Integer> props = new HashMap<>();

}
