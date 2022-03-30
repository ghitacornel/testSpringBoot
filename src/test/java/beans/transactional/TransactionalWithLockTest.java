package beans.transactional;

import beans.transactional.repository.TransactionalEntityRepository;
import beans.transactional.repository.entity.TransactionalEntity;
import beans.transactional.service.TransactionalService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import template.AbstractTestSpringBootContext;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TransactionalWithLockTest extends AbstractTestSpringBootContext {

    @Autowired
    MockMvc mvc;

    @Autowired
    TransactionalService service;

    @Autowired
    TransactionalEntityRepository repository;

    @BeforeEach
    public void cleanDatabase() {
        repository.deleteAll();
        repository.save(new TransactionalEntity(1000, "data with lock"));
    }

    @Test
    public void validateModifyWithLock() throws Exception {
        ExecutorService executor = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        Runnable runnable1 = getTask();
        Runnable runnable2 = getTask();
        Runnable runnable3 = getTask();
        executor.submit(runnable1);
        executor.submit(runnable2);
        executor.submit(runnable3);
        executor.shutdown();
        boolean awaitTermination = executor.awaitTermination(1, TimeUnit.MINUTES);
        if (!awaitTermination) throw new RuntimeException("not all threads finished the job");
        List<TransactionalEntity> all = repository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(1);

        // each thread will wait for obtaining the LOCK and will add his part
        // remove the lock and might get unpredictable result
        Assertions.assertThat(all).contains(new TransactionalEntity(1000, "data with lock 1000 1000 1000"));
    }

    private Runnable getTask() {
        return () -> {
            try {
                System.out.println(Thread.currentThread() + " started invocation of REST endpoint");
                mvc.perform(get("/transactional/validateModifyWithLock/1000"))
                        .andExpect(status().isOk());
                System.out.println(Thread.currentThread() + " ended invocation of REST endpoint");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
