package beans.service;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class AuditService {

    @PersistenceContext
    EntityManager entityManager;

    public List<Number> getRevisions(Class<?> clazz, Object id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        return auditReader.getRevisions(clazz, id);
    }

    public List<Object[]> getAllRevisions(Class<?> clazz, Object id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        return auditReader.createQuery()
                .forRevisionsOfEntity(clazz, false, true)
                .add(AuditEntity.id().eq(id))
                .getResultList();
    }

}
