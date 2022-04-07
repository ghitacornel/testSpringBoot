package beans.validations.repository;

import beans.validations.model.ValidationModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValidationModelRepository extends JpaRepository<ValidationModel, Integer> {
}
