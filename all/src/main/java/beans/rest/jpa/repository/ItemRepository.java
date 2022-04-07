package beans.rest.jpa.repository;

import beans.rest.jpa.model.Item;
import beans.rest.jpa.model.ItemProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    boolean existsByNameContains(String name);

    boolean existsByNameStartsWith(String name);

    boolean existsByNameEndsWith(String name);

    long countByNameContains(String name);

    long countByNameStartsWith(String name);

    long countByNameEndsWith(String name);

    List<Item> findByLengthGreaterThanAndStateIn(Integer minLength, List<Item.State> states);

    List<Item> findByLengthGreaterThanAndWeightLessThanAndStateIn(Integer minLength, Double maxWeight, List<Item.State> states);

    @Query("select new beans.rest.jpa.model.ItemProjection(t.id,t.name) from Item t where t.name like %:value")
    List<ItemProjection> findUsingProjection(@Param("value") String value);

}