package beans.data;

import beans.data.model.Item;
import beans.data.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Rollback
public class SpringDataIT {

    @Autowired
    ItemRepository repository;

    List<Item> items = new ArrayList<>();

    @BeforeEach
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
            items.add(item);
            repository.save(item);
        }
    }

    @Test
    public void findAll() {
        Assertions.assertEquals(items, repository.findAll());
    }

    @Test
    public void findById() {
        Assertions.assertEquals(items.get(0), repository.findById(0).orElseThrow());
    }

    @Transactional// due to returned proxy
    @Test
    public void getOne() {
        Assertions.assertEquals(items.get(0), repository.getReferenceById(0));
    }

    @Test
    public void findAllById() {
        Assertions.assertEquals(items.subList(0, 4), repository.findAllById(List.of(0, 1, 2, 3)));
    }

    @Test
    public void existsById() {
        Assertions.assertTrue(repository.existsById(0));
        Assertions.assertFalse(repository.existsById(-1));
        Assertions.assertTrue(repository.existsByNameContains("1-"));
        Assertions.assertTrue(repository.existsByNameStartsWith("name 1-"));
        Assertions.assertTrue(repository.existsByNameEndsWith("-2"));
    }

    @Test
    public void count() {
        Assertions.assertEquals(20, repository.count());
        Assertions.assertEquals(2, repository.countByNameContains("1-"));
        Assertions.assertEquals(1, repository.countByNameStartsWith("name 1-"));
        Assertions.assertEquals(1, repository.countByNameEndsWith("-2"));
    }

    @Test
    public void find() {
        System.out.println(repository.findByLengthGreaterThanAndStateIn(55, List.of(Item.State.USED, Item.State.NEW)));
        System.out.println(repository.findByLengthGreaterThanAndStateIn(55, List.of()));
        System.out.println(repository.findByLengthGreaterThanAndWeightLessThanAndStateIn(44, 9.9, List.of(Item.State.USED, Item.State.NEW)));
    }

    @Test
    public void findInterfaceDto() {
        Assertions.assertEquals(2, repository.findUsingProjection("2").size());
    }

    @Test
    public void findInterfaceDtoNative() {
        Assertions.assertEquals(2, repository.findUsingProjectionNative("%2").size());
    }

}
