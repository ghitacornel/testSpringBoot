package beans.config.output;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
class OutputDataSourceConfiguration {

    @Value("${spring.datasource.output.url}")
    private String url;

    @Value("${spring.datasource.output.username}")
    private String username;

    @Value("${spring.datasource.output.password}")
    private String password;

    @Value("${spring.datasource.output.driver}")
    private String driver;

    @Bean
    DataSource outputDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);
        return new HikariDataSource(config);
    }

}
