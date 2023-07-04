package beans.transactional.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LongRunningService {

    public String longRunningBusiness() {

        log.info("start long running business");

        // ensure the request takes at least a couple of seconds to complete
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("end long running business");

        return "OK";
    }
}
