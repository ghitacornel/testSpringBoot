package beans.repository;

import beans.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query("select t from Item t where t.name like :name and t.length=:length")
    List<Item> findByNameAndLength(@Param("name") String name, @Param("length") Integer length);

}
