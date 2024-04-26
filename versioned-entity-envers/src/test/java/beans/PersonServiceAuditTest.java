package beans;

import beans.entity.Person;
import beans.entity.Status;
import beans.service.PersonService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.history.Revision;
import org.springframework.data.history.RevisionMetadata;

import java.util.List;

@SpringBootTest
public class PersonServiceAuditTest {

    @Autowired
    PersonService service;

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
            service.save(reference);
            Assertions.assertThat(service.findAll().size()).isEqualTo(1);
            Person person = service.findById(1);

            Assertions.assertThat(person).isEqualTo(reference);

            List<Integer> revisions = service.getAllRevisions(1).stream()
                    .map(Revision::getRevisionNumber)
                    .map(integer -> integer.orElse(null))
                    .toList();
            Assertions.assertThat(revisions.size()).isEqualTo(1);
            Assertions.assertThat(revisions.getFirst()).isEqualTo(1);

            List<RevisionMetadata<Integer>> allRevisions = service.getAllRevisions(1).stream()
                    .map(Revision::getMetadata)
                    .toList();
            Assertions.assertThat(allRevisions.size()).isEqualTo(1);
            Assertions.assertThat(allRevisions.getFirst().getRequiredRevisionNumber()).isEqualTo(1L);
            Assertions.assertThat(allRevisions.getFirst().getRevisionType()).isEqualTo(RevisionMetadata.RevisionType.INSERT);

        }

        reference.setName(reference.getName() + " " + reference.getName());
        {
            service.duplicateName(1);
            Person person = service.findById(1);
            Assertions.assertThat(person).isEqualTo(reference);

            List<Integer> revisions = service.getAllRevisions(1).stream()
                    .map(Revision::getRevisionNumber)
                    .map(integer -> integer.orElse(null))
                    .toList();
            Assertions.assertThat(revisions.size()).isEqualTo(2);
            Assertions.assertThat(revisions.getFirst()).isEqualTo(1);
            Assertions.assertThat(revisions.getLast()).isEqualTo(2);

            List<RevisionMetadata<Integer>> allRevisions = service.getAllRevisions(1).stream()
                    .map(Revision::getMetadata)
                    .toList();
            Assertions.assertThat(allRevisions.size()).isEqualTo(2);
            Assertions.assertThat(allRevisions.getLast().getRequiredRevisionNumber()).isEqualTo(2L);
            Assertions.assertThat(allRevisions.getLast().getRevisionType()).isEqualTo(RevisionMetadata.RevisionType.UPDATE);

        }

        reference.setSalary(2 * reference.getSalary());
        {
            service.duplicateSalary(1);
            Person person = service.findById(1);
            Assertions.assertThat(person).isEqualTo(reference);

            List<Integer> revisions = service.getAllRevisions(1).stream()
                    .map(Revision::getRevisionNumber)
                    .map(integer -> integer.orElse(null))
                    .toList();
            Assertions.assertThat(revisions.size()).isEqualTo(3);
            Assertions.assertThat(revisions.get(0)).isEqualTo(1);
            Assertions.assertThat(revisions.get(1)).isEqualTo(2);
            Assertions.assertThat(revisions.get(2)).isEqualTo(3);

            List<RevisionMetadata<Integer>> allRevisions = service.getAllRevisions(1).stream()
                    .map(Revision::getMetadata)
                    .toList();
            Assertions.assertThat(allRevisions.size()).isEqualTo(3);
            Assertions.assertThat(allRevisions.getLast().getRequiredRevisionNumber()).isEqualTo(3L);
            Assertions.assertThat(allRevisions.getLast().getRevisionType()).isEqualTo(RevisionMetadata.RevisionType.UPDATE);
        }

        reference.setStatus(Status.BUSY);
        {
            service.makeBusy(1);
            Person person = service.findById(1);
            Assertions.assertThat(person).isEqualTo(reference);

            List<Integer> revisions = service.getAllRevisions(1).stream()
                    .map(Revision::getRevisionNumber)
                    .map(integer -> integer.orElse(null))
                    .toList();
            Assertions.assertThat(revisions.size()).isEqualTo(4);
            Assertions.assertThat(revisions.get(0)).isEqualTo(1);
            Assertions.assertThat(revisions.get(1)).isEqualTo(2);
            Assertions.assertThat(revisions.get(2)).isEqualTo(3);
            Assertions.assertThat(revisions.get(3)).isEqualTo(4);

            List<RevisionMetadata<Integer>> allRevisions = service.getAllRevisions(1).stream()
                    .map(Revision::getMetadata)
                    .toList();
            Assertions.assertThat(allRevisions.size()).isEqualTo(4);
            Assertions.assertThat(allRevisions.getLast().getRequiredRevisionNumber()).isEqualTo(4L);
            Assertions.assertThat(allRevisions.getLast().getRevisionType()).isEqualTo(RevisionMetadata.RevisionType.UPDATE);

        }

        {
            service.deleteById(1);
            Assertions.assertThat(service.findAll()).isEmpty();
            Assertions.assertThat(service.findById(1)).isNull();

            List<Integer> revisions = service.getAllRevisions(1).stream()
                    .map(Revision::getRevisionNumber)
                    .map(integer -> integer.orElse(null))
                    .toList();
            Assertions.assertThat(revisions.size()).isEqualTo(5);
            Assertions.assertThat(revisions.get(0)).isEqualTo(1);
            Assertions.assertThat(revisions.get(1)).isEqualTo(2);
            Assertions.assertThat(revisions.get(2)).isEqualTo(3);
            Assertions.assertThat(revisions.get(3)).isEqualTo(4);
            Assertions.assertThat(revisions.get(4)).isEqualTo(5);

            List<RevisionMetadata<Integer>> allRevisions = service.getAllRevisions(1).stream()
                    .map(Revision::getMetadata)
                    .toList();
            Assertions.assertThat(allRevisions.size()).isEqualTo(5);

            reference.setName(null);
            reference.setSalary(null);
            reference.setStatus(null);
            Assertions.assertThat(allRevisions.getLast().getRequiredRevisionNumber()).isEqualTo(5L);
            Assertions.assertThat(allRevisions.getLast().getRevisionType()).isEqualTo(RevisionMetadata.RevisionType.DELETE);

        }

    }
}
