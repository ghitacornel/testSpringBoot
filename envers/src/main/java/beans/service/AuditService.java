package beans.service;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
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

    public <T> List<T> getAllRevisions(Class<T> clazz, Object id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(clazz, true);
        auditQuery.add(AuditEntity.id().eq(id));
        return auditQuery.getResultList();
    }

}
