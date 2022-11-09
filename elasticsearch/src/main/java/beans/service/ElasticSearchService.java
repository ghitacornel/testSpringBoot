package beans.service;

import beans.model.Child;
import beans.model.Parent;
import beans.model.SimpleDataModel;
import beans.projections.ChildProjection;
import beans.projections.ParentProjection;
import lombok.RequiredArgsConstructor;
import org.hibernate.search.engine.search.predicate.SearchPredicate;
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

        List<Parent> parents = searchSession.search(scope).where(booleanJunction.toPredicate()).fetchAllHits();

        for (Parent parent : parents) {
            parent.getChildren().size();
        }

        return parents;
    }

    public List<ParentProjection> findParentProjectionByContent(String content) {
        SearchSession searchSession = Search.session(entityManager);
        SearchScope<Parent> scope = searchSession.scope(Parent.class);
        SearchPredicateFactory predicateFactory = scope.predicate();
        BooleanPredicateClausesStep<?> booleanJunction = predicateFactory.bool();

        booleanJunction.should(predicateFactory.wildcard().field("content").matching(content).toPredicate());
        booleanJunction.should(predicateFactory.wildcard().field("name").matching(content).toPredicate());

        return searchSession.search(scope)
                .select(f -> f.composite(ParentProjection::new,
                        f.field("id", Integer.class),
                        f.field("name", String.class),
                        f.field("content", String.class)
                ))
                .where(booleanJunction.toPredicate())
                .fetchAllHits();
    }

    public List<ParentProjection> findParentProjectionByNestedChildNameAndContent(String content) {
        SearchSession searchSession = Search.session(entityManager);
        SearchScope<Parent> scope = searchSession.scope(Parent.class);
        SearchPredicateFactory predicateFactory = scope.predicate();
        BooleanPredicateClausesStep<?> booleanJunction = predicateFactory.bool();
        booleanJunction.must(predicateFactory.wildcard().field("content").matching("*" + content + "*"));// parents with id  in { 1 2 3 } are matched

        BooleanPredicateClausesStep<?> innerNestedBooleanPredicate = predicateFactory.bool();
        innerNestedBooleanPredicate.must(predicateFactory.wildcard().field("children.name").matching("john"));
        innerNestedBooleanPredicate.must(predicateFactory.wildcard().field("children.content").matching("first"));

        SearchPredicate nestedPredicate = predicateFactory
                .nested()
                .objectField("children")
                .nest(innerNestedBooleanPredicate)
                .toPredicate();
        booleanJunction.must(nestedPredicate);// only parent with child john+first is matched

        return searchSession.search(scope)
                .select(f -> f.composite(ParentProjection::new,
                        f.field("id", Integer.class),
                        f.field("name", String.class),
                        f.field("content", String.class)
                ))
                .where(booleanJunction.toPredicate())
                .fetchAllHits();
    }

    public List<Child> findChildByContent(String content) {
        SearchSession searchSession = Search.session(entityManager);
        SearchScope<Child> scope = searchSession.scope(Child.class);
        SearchPredicateFactory predicateFactory = scope.predicate();
        BooleanPredicateClausesStep<?> booleanJunction = predicateFactory.bool();

        booleanJunction.should(predicateFactory.wildcard().field("content").matching(content).toPredicate());
        booleanJunction.should(predicateFactory.wildcard().field("name").matching(content).toPredicate());

        return searchSession.search(scope)
                .where(booleanJunction.toPredicate())
                .fetchAllHits();
    }

    public List<ChildProjection> findChildProjectionByContent(String content) {
        SearchSession searchSession = Search.session(entityManager);
        SearchScope<Child> scope = searchSession.scope(Child.class);
        SearchPredicateFactory predicateFactory = scope.predicate();
        BooleanPredicateClausesStep<?> booleanJunction = predicateFactory.bool();

        booleanJunction.should(predicateFactory.wildcard().field("content").matching(content).toPredicate());
        booleanJunction.should(predicateFactory.wildcard().field("name").matching(content).toPredicate());

        return searchSession.search(scope)
                .select(f -> f.composite(ChildProjection::new,
                        f.field("id", Integer.class),
                        f.field("name", String.class),
                        f.field("content", String.class)
                ))
                .where(booleanJunction.toPredicate())
                .fetchAllHits();
    }

    public List<SimpleDataModel> findSimpleByContent(String content) {
        SearchSession searchSession = Search.session(entityManager);
        SearchScope<SimpleDataModel> scope = searchSession.scope(SimpleDataModel.class);
        SearchPredicateFactory predicateFactory = scope.predicate();
        BooleanPredicateClausesStep<?> booleanJunction = predicateFactory.bool();

        booleanJunction.should(predicateFactory.match().field("content").matching(content).toPredicate());
        booleanJunction.should(predicateFactory.match().field("name").matching(content).toPredicate());

        return searchSession.search(scope)
                .where(booleanJunction.toPredicate())
                .fetchAllHits();
    }

}
