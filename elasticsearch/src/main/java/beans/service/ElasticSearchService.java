package beans.service;

import beans.model.Child;
import beans.model.Parent;
import beans.model.SimpleDataModel;
import beans.projections.ChildProjection;
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

    public List<Parent> findParentByContent(String content) {
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

    public List<Child> findChildByContent(String content) {
        SearchSession searchSession = Search.session(entityManager);
        SearchScope<Child> scope = searchSession.scope(Child.class);
        SearchPredicateFactory predicateFactory = scope.predicate();
        BooleanPredicateClausesStep<?> booleanJunction = predicateFactory.bool();

        booleanJunction.should(predicateFactory.wildcard().field("content").matching(content).toPredicate());
        booleanJunction.should(predicateFactory.wildcard().field("name").matching(content).toPredicate());

        return searchSession.search(Child.class)
                .where(booleanJunction.toPredicate())
                .fetchAllHits();
    }

    public List<SimpleDataModel> findSimpleByContent(String content) {
        SearchSession searchSession = Search.session(entityManager);
        SearchScope<SimpleDataModel> scope = searchSession.scope(SimpleDataModel.class);
        SearchPredicateFactory predicateFactory = scope.predicate();
        BooleanPredicateClausesStep<?> booleanJunction = predicateFactory.bool();

        booleanJunction.should(predicateFactory.wildcard().field("content").matching(content).toPredicate());
        booleanJunction.should(predicateFactory.wildcard().field("name").matching(content).toPredicate());

        return searchSession.search(SimpleDataModel.class)
                .where(booleanJunction.toPredicate())
                .fetchAllHits();
    }

    public List<ChildProjection> findChildProjectionByContent(String content) {
        SearchSession searchSession = Search.session(entityManager);
        SearchScope<ChildProjection> scope = searchSession.scope(ChildProjection.class);
        SearchPredicateFactory predicateFactory = scope.predicate();
        BooleanPredicateClausesStep<?> booleanJunction = predicateFactory.bool();

        booleanJunction.should(predicateFactory.wildcard().field("content").matching(content).toPredicate());
        booleanJunction.should(predicateFactory.wildcard().field("name").matching(content).toPredicate());

        return searchSession.search(ChildProjection.class)
                .where(booleanJunction.toPredicate())
                .fetchAllHits();
    }
}
