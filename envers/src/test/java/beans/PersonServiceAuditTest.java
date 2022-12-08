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

        Assertions.assertThat(service.findAll()).isEmpty();
        Assertions.assertThat(service.findById(1)).isNull();

        Person reference = new Person();
        reference.setId(1);
        reference.setName("name");
        reference.setSalary(111.222);
        reference.setStatus(Status.OOO);
        config.user = "Ghita";
        service.save(reference);
        Assertions.assertThat(service.findAll().size()).isEqualTo(1);
        Assertions.assertThat(service.findById(1)).isEqualTo(reference);
        System.err.println(service.findById(1));

        reference.setName(reference.getName() + " " + reference.getName());
        config.user = "Cornel";
        service.duplicateName(1);
        Assertions.assertThat(service.findAll().size()).isEqualTo(1);
        Assertions.assertThat(service.findById(1)).isEqualTo(reference);
        System.err.println(service.findById(1));

        reference.setSalary(2 * reference.getSalary());
        service.duplicateSalary(1);
        Assertions.assertThat(service.findAll().size()).isEqualTo(1);
        Assertions.assertThat(service.findById(1)).isEqualTo(reference);
        System.err.println(service.findById(1));

        reference.setStatus(Status.BUSY);
        service.makeBusy(1);
        Assertions.assertThat(service.findAll().size()).isEqualTo(1);
        Assertions.assertThat(service.findById(1)).isEqualTo(reference);
        System.err.println(service.findById(1));

        service.deleteById(1);
        Assertions.assertThat(service.findAll()).isEmpty();
        Assertions.assertThat(service.findById(1)).isNull();

    }
}
