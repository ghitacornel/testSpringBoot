package beans.config.input;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "inputEntityManager",
        basePackages = {"beans.config.input"},
        transactionManagerRef = "inputTransactionManager"
)
class InputConfiguration {

    @Value("${spring.datasource.input.database.action}")
    private String action;

    @Bean
    LocalContainerEntityManagerFactoryBean inputEntityManager(@Qualifier("inputDataSource") DataSource dataSource, EntityManagerFactoryBuilder builder) {
        Map<String, String> map = new HashMap<>();
        map.put("hibernate.show_sql", "true");
        map.put("hibernate.format_sql", "false");
        map.put("javax.persistence.schema-generation.database.action", action);
        return builder
                .dataSource(dataSource)
                .packages("beans.config.input.entity")
                .persistenceUnit("inputPU")
                .properties(map)
                .build();
    }

    @Bean
    JpaTransactionManager inputTransactionManager(@Qualifier("inputEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
