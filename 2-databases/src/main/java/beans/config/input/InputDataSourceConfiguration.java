package beans.config.input;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
class InputDataSourceConfiguration {

    @Value("${spring.datasource.input.url}")
    private String url;

    @Value("${spring.datasource.input.username}")
    private String username;

    @Value("${spring.datasource.input.password}")
    private String password;

    @Bean
    DataSource inputDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        return new HikariDataSource(config);
    }

}
