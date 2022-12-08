package beans;

import beans.config.PersistenceConfig;
import beans.entity.Person;
import beans.entity.Status;
import beans.service.PersonService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonServiceAuditTest {

    @Autowired
    PersonService service;

    @Autowired
    PersistenceConfig config;

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
            System.err.println(person);
            reference.setModifiedDate(person.getModifiedDate());
        }

        {
            service.deleteById(1);
            Assertions.assertThat(service.findAll()).isEmpty();
            Assertions.assertThat(service.findById(1)).isNull();
        }

    }
}
