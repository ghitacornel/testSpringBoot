package beans.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
class TransactionManagementConfiguration {

    @Primary
    @Bean
    PlatformTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new JdbcTransactionManager(dataSource);
    }

}
