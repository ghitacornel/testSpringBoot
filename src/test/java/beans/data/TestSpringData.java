package beans.data;

import beans.rest.jpa.model.Item;
import beans.rest.jpa.repository.ItemRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import template.AbstractTestSpringBootContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Rollback
public class TestSpringData extends AbstractTestSpringBootContext {

    @Autowired
    ItemRepository repository;

    List<Item> items = new ArrayList<>();

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
            items.add(item);
            repository.save(item);
        }
    }

    @Test
    public void findAll() {
        Assert.assertEquals(items, repository.findAll());
    }

    @Test
    public void findById() {
        Assert.assertEquals(items.get(0), repository.findById(0).orElseThrow());
    }

    @Transactional// due to returned proxy
    @Test
    public void getOne() {
        Assert.assertEquals(items.get(0), repository.getById(0));
    }

    @Test
    public void findAllById() {
        Assert.assertEquals(items.subList(0, 4), repository.findAllById(List.of(0, 1, 2, 3)));
    }

    @Test
    public void existsById() {
        Assert.assertTrue(repository.existsById(0));
        Assert.assertFalse(repository.existsById(-1));
        Assert.assertTrue(repository.existsByNameContains("1-"));
        Assert.assertTrue(repository.existsByNameStartsWith("name 1-"));
        Assert.assertTrue(repository.existsByNameEndsWith("-2"));
    }

    @Test
    public void count() {
        Assert.assertEquals(20, repository.count());
        Assert.assertEquals(2, repository.countByNameContains("1-"));
        Assert.assertEquals(1, repository.countByNameStartsWith("name 1-"));
        Assert.assertEquals(1, repository.countByNameEndsWith("-2"));
    }

    @Test
    public void find() {
        System.out.println(repository.findByLengthGreaterThanAndStateIn(55, List.of(Item.State.USED, Item.State.NEW)));
        System.out.println(repository.findByLengthGreaterThanAndStateIn(55, List.of()));
        System.out.println(repository.findByLengthGreaterThanAndWeightLessThanAndStateIn(44, 9.9, List.of(Item.State.USED, Item.State.NEW)));
    }

    @Test
    public void findInterfaceDto() {
        Assert.assertEquals(2, repository.findUsingProjection("2").size());
    }

}
