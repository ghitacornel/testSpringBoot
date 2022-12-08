package beans.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class PersistenceConfig {

    public String user;

    @Bean
    AuditorAware<String> auditorProvider() {
        return (AuditorAware) () -> Optional.of(user);
    }

}
