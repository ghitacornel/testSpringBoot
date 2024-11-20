package beans.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@EnableSolrRepositories(
        basePackages = "beans.repositories",
        namedQueriesLocation = "classpath:solr-named-queries.properties"
)
class SolrConfig {

    @Bean
    SolrClient solrClient() {
        return new HttpSolrClient.Builder("http://localhost:8981/solr").build();
    }

    @Bean
    SolrTemplate solrTemplate(SolrClient client) {
        return new SolrTemplate(client);
    }

}
