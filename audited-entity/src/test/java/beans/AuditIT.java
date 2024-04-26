package beans;

import beans.entities.AuditedPerson;
import beans.services.AuditedPersonService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional(propagation = Propagation.NEVER)
class AuditIT {

    @Autowired
    AuditedPersonService service;

    @Test
    void testCrud() {

        AuditedPerson person = AuditedPerson.builder()
                .id(1L)
                .name("john")
                .salary(1.1)
                .build();

        service.save(person);

        {
            AuditedPerson persistedPerson = service.findById(person.getId());
            Assertions.assertThat(persistedPerson).isNotNull();
            Assertions.assertThat(persistedPerson.getId()).isEqualTo(person.getId());
            Assertions.assertThat(persistedPerson.getName()).isEqualTo(person.getName());
            Assertions.assertThat(persistedPerson.getSalary()).isEqualTo(person.getSalary());
            Assertions.assertThat(persistedPerson.getCreatedDate()).isNotNull();
            Assertions.assertThat(persistedPerson.getCreatedBy()).isEqualTo("Cornel");
            Assertions.assertThat(persistedPerson.getModifiedDate()).isNotNull();
            Assertions.assertThat(persistedPerson.getModifiedBy()).isEqualTo("Cornel");
            Assertions.assertThat(persistedPerson.getCreatedDate()).isEqualTo(persistedPerson.getModifiedDate());
        }

        service.doubleSalary(person.getId());

        {
            AuditedPerson persistedPerson = service.findById(person.getId());
            Assertions.assertThat(persistedPerson).isNotNull();
            Assertions.assertThat(persistedPerson.getId()).isEqualTo(person.getId());
            Assertions.assertThat(persistedPerson.getName()).isEqualTo(person.getName());
            Assertions.assertThat(persistedPerson.getSalary()).isEqualTo(person.getSalary()*2);
            Assertions.assertThat(persistedPerson.getCreatedDate()).isNotNull();
            Assertions.assertThat(persistedPerson.getCreatedBy()).isEqualTo("Cornel");
            Assertions.assertThat(persistedPerson.getModifiedDate()).isNotNull();
            Assertions.assertThat(persistedPerson.getModifiedBy()).isEqualTo("Cornel");
            Assertions.assertThat(persistedPerson.getCreatedDate()).isBefore(persistedPerson.getModifiedDate());
        }

    }
}
