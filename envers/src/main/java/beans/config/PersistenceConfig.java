package beans.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class PersistenceConfig {

    public String user;

    @Bean
    AuditorAware<String> auditorProvider() {
        return () -> Optional.of(user);
    }

}
