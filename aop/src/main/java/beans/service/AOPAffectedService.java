package beans.service;

import beans.aop.LogExecutionTime;
import org.springframework.stereotype.Service;

@Service
public class AOPAffectedService {

    public String method1() {
        return "method1";
    }

    @LogExecutionTime
    public String method2() {
        return "method2";
    }
}
