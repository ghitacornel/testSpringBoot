package beans.repository;

import beans.model.SimpleDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimpleDataModelRepository extends JpaRepository<SimpleDataModel, Integer> {
}
