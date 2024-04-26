package beans.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
class AuditConfig {

    // "logged user"
    public String user = "Cornel";

    @Bean
    AuditorAware<String> auditorProvider() {
        return () -> Optional.of(user);
    }

}
