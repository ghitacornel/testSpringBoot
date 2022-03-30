package beans.transactional.repository;

import beans.transactional.repository.entity.TransactionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;

public interface TransactionalEntityRepository extends JpaRepository<TransactionalEntity, Integer> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select t from TransactionalEntity t where t.id = :id")
    TransactionalEntity getByIdWithLock(@Param("id") Integer id);

}
