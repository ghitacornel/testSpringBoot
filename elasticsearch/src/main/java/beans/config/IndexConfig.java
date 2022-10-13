package beans.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Configuration
@RequiredArgsConstructor
@Slf4j
@Transactional
public class IndexConfig {

    private final EntityManager em;

    @PostConstruct
    public void initIndex() throws InterruptedException {
        log.info("Initiating indexing...");
//        SearchSession session = Search.session(em);
//        session.massIndexer().start();
        log.info("All entities indexed");
    }
}
