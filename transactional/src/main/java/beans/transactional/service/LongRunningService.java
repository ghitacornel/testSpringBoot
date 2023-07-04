package beans.transactional.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
@Slf4j
public class LongRunningService {

    public String longRunningBusiness() {

        log.info("start long running business");

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<String> future = executorService.submit(() -> {

            // ensure the request takes at least a couple of seconds to complete
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return "OK";

        });

        executorService.shutdown();


        try {
            String result = future.get();
            log.info("end long running business");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("muie", e);
        }

    }
}
