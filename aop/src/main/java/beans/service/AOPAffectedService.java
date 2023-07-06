package beans.service;

import beans.aop.LogExecutionTime;
import org.springframework.stereotype.Service;

@Service
public class AOPAffectedService {

    public String method1() {
        return "method1";
    }

    @LogExecutionTime(timeout = 750)
    public String method2() {

        // ensure execution time exceeds warning threshold
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "method2";
    }
}
