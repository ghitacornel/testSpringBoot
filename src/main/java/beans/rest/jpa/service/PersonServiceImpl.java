package beans.rest.jpa.service;

import beans.rest.exceptions.CustomBusinessException;
import beans.rest.jpa.model.Person;
import beans.rest.jpa.repository.CustomPersonRepository;
import beans.rest.jpa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository repository;

    @Autowired
    @Lazy
    PersonService SELF;

    @Autowired
    CustomPersonRepository customPersonRepository;

    public List<Person> findAll() {
        return repository.findAll();
    }

    public Person findById(Integer id) {
        return repository.findById(id).get();
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public void save(Person person) {
        repository.save(person);
    }

    public List<Person> findByPassword(String password) {
        return customPersonRepository.findByPassword(password);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void validate2TransactionsArePresent() {

        Person person1 = new Person();
        person1.setId(111);
        person1.setName("name 111");
        repository.save(person1);

        if (true) throw new CustomBusinessException("Validate 2 Transactions Are Present");

        Person person2 = new Person();
        person2.setId(222);
        person2.setName("name 222");
        repository.save(person2);

    }

    @Transactional
    public void validate1TransactionIsPresent() {

        Person person1 = new Person();
        person1.setId(111);
        person1.setName("name 111");
        repository.save(person1);

        if (true) throw new CustomBusinessException("Validate 1 Transaction Is Present");

        Person person2 = new Person();
        person2.setId(222);
        person2.setName("name 222");
        repository.save(person2);

    }

    @Transactional
    public void firstMethodTransactionalFailsAtTheEndSecondMethodTHISInvoked() {

        Person person1 = new Person();
        person1.setId(111);
        person1.setName("name 111");
        repository.save(person1);

        this.methodWithTransactionRequiresNew();

        if (true) throw new CustomBusinessException("Invocation with this. does not honor propagation level");

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void methodWithTransactionRequiresNew() {

        Person person2 = new Person();
        person2.setId(222);
        person2.setName("name 222");
        repository.save(person2);

    }

    @Transactional
    public void firstMethodTransactionalFailsAtTheEndSecondMethodSELFInvoked() {

        Person person1 = new Person();
        person1.setId(111);
        person1.setName("name 111");
        repository.save(person1);

        SELF.methodWithTransactionRequiresNew();

        if (true) throw new CustomBusinessException("Invocation with SELF does honor propagation level");

    }
}
