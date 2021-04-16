package beans.data;

import beans.rest.jpa.model.Item;
import beans.rest.jpa.repository.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import template.AbstractTestSpringBootContext;

import java.time.LocalDateTime;
import java.util.List;

@Rollback
public class TestSpringData extends AbstractTestSpringBootContext {

    @Autowired
    ItemRepository repository;

    @Before
    public void setUp() {
        for (int i = 0; i < 20; i++) {
            Item item = new Item();
            item.setId(i);
            item.setName("name " + i + "-" + i);
            item.setFake(i % 2 == 0);
            item.setLength(i * 10 + i);
            item.setWeight((i * 10 + i) / (double) 10);
            item.setRegistration(LocalDateTime.now().plusDays(i));
            item.setState(Item.State.values()[i % 3]);
            repository.save(item);
        }
    }

    @Test
    public void findAll() {
        System.out.println(repository.findAll());
    }

    @Test
    public void findById() {
        System.out.println(repository.findById(0));
    }

    @Transactional// due to returned proxy
    @Test
    public void getOne() {
        System.out.println(repository.getOne(0));
    }

    @Test
    public void findAllById() {
        System.out.println(repository.findAllById(List.of(0, 1, 2, 3)));
    }

    @Test
    public void existsById() {
        System.out.println(repository.existsById(0));
        System.out.println(repository.existsById(-1));
        System.out.println(repository.existsByNameContains("1-"));
        System.out.println(repository.existsByNameStartsWith("name 1-"));
        System.out.println(repository.existsByNameEndsWith("-2"));
    }

    @Test
    public void count() {
        System.out.println(repository.count());
        System.out.println(repository.countByNameContains("1-"));
        System.out.println(repository.countByNameStartsWith("name 1-"));
        System.out.println(repository.countByNameEndsWith("-2"));
    }

    @Test
    public void find() {
        System.out.println(repository.findByLengthGreaterThanAndStateIn(55, List.of(Item.State.USED, Item.State.NEW)));
        System.out.println(repository.findByLengthGreaterThanAndStateIn(55, List.of()));
        System.out.println(repository.findByLengthGreaterThanAndWeightLessThanAndStateIn(44, 9.9, List.of(Item.State.USED, Item.State.NEW)));
    }

    @Test
    public void findInterfaceDto() {
        System.out.println(repository.findByNameEndsWith("2"));
    }

}
