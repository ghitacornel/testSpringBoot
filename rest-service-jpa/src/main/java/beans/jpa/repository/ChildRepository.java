package beans.jpa.repository;

import beans.jpa.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ChildRepository extends JpaRepository<Child, Integer> {

    @Query("select distinct t.name from Child t")
    Set<String> findOnlyNames();

}
