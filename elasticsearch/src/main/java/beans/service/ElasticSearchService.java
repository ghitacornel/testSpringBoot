package beans.service;

import beans.model.Parent;
import lombok.RequiredArgsConstructor;
import org.hibernate.search.engine.search.predicate.dsl.BooleanPredicateClausesStep;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.scope.SearchScope;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ElasticSearchService {

    private final EntityManager entityManager;

    public List<Parent> findByContent(String content) {
        SearchSession searchSession = Search.session(entityManager);
        SearchScope<Parent> scope = searchSession.scope(Parent.class);
        SearchPredicateFactory predicateFactory = scope.predicate();
        BooleanPredicateClausesStep<?> booleanJunction = predicateFactory.bool();

        booleanJunction.should(predicateFactory.wildcard().field("content").matching(content).toPredicate());

        List<Parent> parents = searchSession.search(Parent.class).where(booleanJunction.toPredicate()).fetchAllHits();

        for (Parent parent : parents) {
            parent.getChildren().size();
        }

        return parents;
    }
}
