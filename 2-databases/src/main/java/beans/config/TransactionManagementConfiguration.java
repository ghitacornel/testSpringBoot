package beans.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;

@Configuration
@EnableTransactionManagement
class TransactionManagementConfiguration {

    @Primary// not that nice
    @Bean("chainedTransactionManager")
    PlatformTransactionManager transactionManager(
            @Qualifier("inputTransactionManager") PlatformTransactionManager transactionManager1,
            @Qualifier("outputTransactionManager") PlatformTransactionManager transactionManager2
    ) {
        return new ChainedTransactionManager(transactionManager1, transactionManager2);
    }

    @Bean
    EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }

}
