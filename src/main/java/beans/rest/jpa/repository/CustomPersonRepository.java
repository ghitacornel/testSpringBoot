package beans.rest.jpa.repository;

import beans.rest.jpa.model.Person;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CustomPersonRepository extends SimpleJpaRepository<Person, Integer> {

    private EntityManager em;

    public CustomPersonRepository(EntityManager em) {
        super(Person.class, em);
        this.em = em;
    }

    public List<Person> findByPassword(String password) {
        return em.createNamedQuery("Person.findByPassword", Person.class)
                .setParameter("password", password)
                .getResultList();
    }
}
