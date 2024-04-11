package beans.config.output;

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
        entityManagerFactoryRef = "outputEntityManager",
        basePackages = {"beans.config.output"},
        transactionManagerRef = "outputTransactionManager"
)
class OutputConfiguration {

    @Value("${spring.datasource.output.database.action}")
    private String action;

    @Bean
    LocalContainerEntityManagerFactoryBean outputEntityManager(@Qualifier("outputDataSource") DataSource dataSource, EntityManagerFactoryBuilder builder) {
        Map<String, String> map = new HashMap<>();
        map.put("hibernate.show_sql", "true");
        map.put("hibernate.format_sql", "false");
        map.put("javax.persistence.schema-generation.database.action", action);
        return builder
                .dataSource(dataSource)
                .packages("jpa.configuration.output.entity")
                .persistenceUnit("outputPU")
                .properties(map)
                .build();
    }

    @Bean
    JpaTransactionManager outputTransactionManager(@Qualifier("outputEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
