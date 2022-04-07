package beans.transactional;

import beans.transactional.repository.TransactionalEntityRepository;
import beans.transactional.repository.entity.TransactionalEntity;
import beans.transactional.service.TransactionalService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionalTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    TransactionalService service;

    @Autowired
    TransactionalEntityRepository repository;

    @BeforeEach
    public void cleanDatabase() {
        repository.deleteAll();
    }

    @Test
    public void validate2WritesSeemsOk() throws Exception {
        mvc.perform(get("/transactional/validate2WritesSeemsOk"))
                .andExpect(status().isOk());

        List<TransactionalEntity> all = repository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);
        Assertions.assertThat(all).contains(new TransactionalEntity(1, "data1"), new TransactionalEntity(2, "data2"));
    }

    @Test
    public void validate2TransactionsArePresent() {
        try {
            mvc.perform(get("/transactional/validate2TransactionsArePresent"));
        } catch (Exception e) {
            // don't care, we expect it
        }

        List<TransactionalEntity> all = repository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(1);
        Assertions.assertThat(all).contains(new TransactionalEntity(1, "data1"));
    }

    @Test
    public void validate1TransactionIsPresent() {
        try {
            mvc.perform(get("/transactional/validate1TransactionIsPresent"));
        } catch (Exception e) {
            // don't care, we expect it
        }

        List<TransactionalEntity> all = repository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(0);
    }

    @Test
    public void validateTransactionRequiresNewIsNotHonoredWhenUsingTHIS() {
        try {
            mvc.perform(get("/transactional/validateTransactionRequiresNewIsNotHonoredWhenUsingTHIS"));
        } catch (Exception e) {
            // don't care, we expect it
        }

        List<TransactionalEntity> all = repository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(0);
    }

    @Test
    public void validateTransactionRequiresNewIsHonoredWhenUsingSELF() {
        try {
            mvc.perform(get("/transactional/validateTransactionRequiresNewIsHonoredWhenUsingSELF"));
        } catch (Exception e) {
            // don't care, we expect it
        }

        List<TransactionalEntity> all = repository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(1);
        Assertions.assertThat(all).contains(new TransactionalEntity(2, "data2"));
    }
}
