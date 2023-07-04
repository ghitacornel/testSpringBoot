package beans.transactional.service;

import org.springframework.stereotype.Service;

@Service
public class LongRunningService {

    public String longRunningBusiness() {

        // ensure the request takes at least a couple of seconds to complete
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "OK";
    }
}
