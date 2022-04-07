package beans.data.repository;

import beans.data.model.Item;
import beans.data.model.ItemProjection;
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

    @Query("select new beans.data.model.ItemProjection(t.id,t.name) from Item t where t.name like %:value")
    List<ItemProjection> findUsingProjection(@Param("value") String value);

}
