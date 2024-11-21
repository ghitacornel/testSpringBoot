package beans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableElasticsearchRepositories
public class ElasticsearchMain {
    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchMain.class, args);
    }
}
