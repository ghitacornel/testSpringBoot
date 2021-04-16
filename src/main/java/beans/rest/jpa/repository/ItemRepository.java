package beans.rest.jpa.repository;

import beans.rest.jpa.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    boolean existsById(Integer id);

    boolean existsByNameContains(String name);

    boolean existsByNameStartsWith(String name);

    boolean existsByNameEndsWith(String name);

    long countByNameContains(String name);

    long countByNameStartsWith(String name);

    long countByNameEndsWith(String name);

    List<Item> findByLengthGreaterThanAndStateIn(Integer minLength, List<Item.State> states);

    List<Item> findByLengthGreaterThanAndWeightLessThanAndStateIn(Integer minLength, Double maxWeight, List<Item.State> states);

    List<Item.ItemProjection> findByNameEndsWith(String value);

}
