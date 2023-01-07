package beans;

import beans.service.AuditService;
import beans.config.PersistenceConfig;
import beans.entity.Person;
import beans.entity.Status;
import beans.service.PersonService;
import org.assertj.core.api.Assertions;
import org.hibernate.envers.RevisionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PersonServiceAuditTest {

    @Autowired
    PersonService service;

    @Autowired
    PersistenceConfig config;

    @Autowired
    AuditService auditService;

    @BeforeEach
    public void setUp() {
        service.deleteAll();
    }

    @Test
    public void testCrud() {

        {
            Assertions.assertThat(service.findAll()).isEmpty();
            Assertions.assertThat(service.findById(1)).isNull();
        }

        Person reference = new Person();
        reference.setId(1);
        reference.setName("name");
        reference.setSalary(111.222);
        reference.setStatus(Status.OOO);
        {
            config.user = "Ghita";
            service.save(reference);
            Assertions.assertThat(service.findAll().size()).isEqualTo(1);
            Person person = service.findById(1);

            Assertions.assertThat(person).isEqualTo(reference);
            Assertions.assertThat(person.getCreatedBy()).isEqualTo("Ghita");
            Assertions.assertThat(person.getCreatedDate()).isPositive();
            Assertions.assertThat(person.getModifiedBy()).isEqualTo("Ghita");
            Assertions.assertThat(person.getModifiedDate()).isPositive();

            List<Number> revisions = auditService.getRevisions(Person.class, 1);
            Assertions.assertThat(revisions.size()).isEqualTo(1);
            Assertions.assertThat(revisions.get(0)).isEqualTo(1);

            List<Object[]> allRevisions = auditService.getAllRevisions(Person.class, 1);
            Assertions.assertThat(allRevisions.size()).isEqualTo(1);
            Assertions.assertThat(allRevisions.get(0)[0]).isEqualTo(reference);
            Assertions.assertThat(allRevisions.get(0)[2]).isEqualTo(RevisionType.ADD);

            System.err.println(person);
            reference.setCreatedDate(person.getCreatedDate());
            reference.setModifiedDate(person.getModifiedDate());
        }

        reference.setName(reference.getName() + " " + reference.getName());
        {
            config.user = "Cornel";
            service.duplicateName(1);
            Person person = service.findById(1);
            Assertions.assertThat(person).isEqualTo(reference);
            Assertions.assertThat(person.getCreatedBy()).isEqualTo("Ghita");
            Assertions.assertThat(person.getCreatedDate()).isEqualTo(reference.getCreatedDate());
            Assertions.assertThat(person.getModifiedBy()).isEqualTo("Cornel");
            Assertions.assertThat(person.getModifiedDate()).isGreaterThan(reference.getModifiedDate());

            List<Number> revisions = auditService.getRevisions(Person.class, 1);
            Assertions.assertThat(revisions.size()).isEqualTo(2);
            Assertions.assertThat(revisions.get(0)).isEqualTo(1);
            Assertions.assertThat(revisions.get(1)).isEqualTo(2);

            List<Object[]> allRevisions = auditService.getAllRevisions(Person.class, 1);
            Assertions.assertThat(allRevisions.size()).isEqualTo(2);
            Assertions.assertThat(allRevisions.get(1)[0]).isEqualTo(reference);
            Assertions.assertThat(allRevisions.get(1)[2]).isEqualTo(RevisionType.MOD);

            System.err.println(person);
            reference.setModifiedDate(person.getModifiedDate());
        }

        reference.setSalary(2 * reference.getSalary());
        {
            service.duplicateSalary(1);
            Person person = service.findById(1);
            Assertions.assertThat(person).isEqualTo(reference);
            Assertions.assertThat(person.getCreatedBy()).isEqualTo("Ghita");
            Assertions.assertThat(person.getCreatedDate()).isEqualTo(reference.getCreatedDate());
            Assertions.assertThat(person.getModifiedBy()).isEqualTo("Cornel");
            Assertions.assertThat(person.getModifiedDate()).isGreaterThan(reference.getModifiedDate());

            List<Number> revisions = auditService.getRevisions(Person.class, 1);
            Assertions.assertThat(revisions.size()).isEqualTo(3);
            Assertions.assertThat(revisions.get(0)).isEqualTo(1);
            Assertions.assertThat(revisions.get(1)).isEqualTo(2);
            Assertions.assertThat(revisions.get(2)).isEqualTo(3);

            List<Object[]> allRevisions = auditService.getAllRevisions(Person.class, 1);
            Assertions.assertThat(allRevisions.size()).isEqualTo(3);
            Assertions.assertThat(allRevisions.get(2)[0]).isEqualTo(reference);
            Assertions.assertThat(allRevisions.get(2)[2]).isEqualTo(RevisionType.MOD);

            System.err.println(person);
            reference.setModifiedDate(person.getModifiedDate());
        }

        reference.setStatus(Status.BUSY);
        {
            service.makeBusy(1);
            Person person = service.findById(1);
            Assertions.assertThat(person).isEqualTo(reference);
            Assertions.assertThat(person.getCreatedBy()).isEqualTo("Ghita");
            Assertions.assertThat(person.getCreatedDate()).isEqualTo(reference.getCreatedDate());
            Assertions.assertThat(person.getModifiedBy()).isEqualTo("Cornel");
            Assertions.assertThat(person.getModifiedDate()).isGreaterThan(reference.getModifiedDate());

            List<Number> revisions = auditService.getRevisions(Person.class, 1);
            Assertions.assertThat(revisions.size()).isEqualTo(4);
            Assertions.assertThat(revisions.get(0)).isEqualTo(1);
            Assertions.assertThat(revisions.get(1)).isEqualTo(2);
            Assertions.assertThat(revisions.get(2)).isEqualTo(3);
            Assertions.assertThat(revisions.get(3)).isEqualTo(4);

            List<Object[]> allRevisions = auditService.getAllRevisions(Person.class, 1);
            Assertions.assertThat(allRevisions.size()).isEqualTo(4);
            Assertions.assertThat(allRevisions.get(3)[0]).isEqualTo(reference);
            Assertions.assertThat(allRevisions.get(3)[2]).isEqualTo(RevisionType.MOD);

            System.err.println(person);
            reference.setModifiedDate(person.getModifiedDate());
        }

        {
            service.deleteById(1);
            Assertions.assertThat(service.findAll()).isEmpty();
            Assertions.assertThat(service.findById(1)).isNull();

            List<Number> revisions = auditService.getRevisions(Person.class, 1);
            Assertions.assertThat(revisions.size()).isEqualTo(5);
            Assertions.assertThat(revisions.get(0)).isEqualTo(1);
            Assertions.assertThat(revisions.get(1)).isEqualTo(2);
            Assertions.assertThat(revisions.get(2)).isEqualTo(3);
            Assertions.assertThat(revisions.get(3)).isEqualTo(4);
            Assertions.assertThat(revisions.get(4)).isEqualTo(5);

            List<Object[]> allRevisions = auditService.getAllRevisions(Person.class, 1);
            Assertions.assertThat(allRevisions.size()).isEqualTo(5);

            reference.setName(null);
            reference.setSalary(null);
            reference.setStatus(null);
            reference.setCreatedDate(0);
            reference.setModifiedDate(0);
            reference.setCreatedBy(null);
            reference.setModifiedBy(null);
            Assertions.assertThat(allRevisions.get(4)[0]).isEqualTo(reference);
            Assertions.assertThat(allRevisions.get(4)[2]).isEqualTo(RevisionType.DEL);

        }

    }
}