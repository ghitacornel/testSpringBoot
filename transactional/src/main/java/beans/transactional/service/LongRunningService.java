package beans.transactional.service;

import beans.transactional.repository.TransactionalEntityRepository;
import beans.transactional.repository.entity.TransactionalEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
@Slf4j
public class LongRunningService {

    private final ExecutorService executorService = Executors.newFixedThreadPool(5);
    private final TransactionalEntityRepository repository;

    public String longRunningBusiness() {
        log.info("start long running business");

        for (TransactionalEntity transactionalEntity : repository.findAll()) {
            log.info("loaded from db for simulating a DB interaction " + transactionalEntity);
        }

        Future<String> future = executorService.submit(() -> {

            // ensure the request takes at least a couple of seconds to complete
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return "OK";

        });

        String result;
        try {
            result = future.get();
        } catch (Exception e) {
            throw new RuntimeException("bad luck", e);
        }

        log.info("end long running business");
        return result;
    }
}
