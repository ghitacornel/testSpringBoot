package beans.transactional.service;

import beans.transactional.repository.TransactionalEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LongRunningService {

    private final ExecutorService executorService = Executors.newFixedThreadPool(5);
    private final TransactionalEntityRepository repository;

    public String longRunningBusiness(Integer seconds) {
        log.info("start long running business");

        Future<String> future = executorService.submit(() -> {

            // ensure the request takes at least a couple of seconds to complete
            try {
                Thread.sleep(seconds);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return "OK";

        });

        // first wait
        String result;
        try {
            result = future.get();
        } catch (Exception e) {
            throw new RuntimeException("bad luck", e);
        }

        // second do a db call
        // looks like it doesn't work without this
        log.info("loaded from db for simulating a DB interaction " + repository.findAll());

        log.info("end long running business");
        return result;
    }
}
