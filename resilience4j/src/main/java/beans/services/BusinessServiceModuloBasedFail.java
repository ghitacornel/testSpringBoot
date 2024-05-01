package beans.services;

import org.springframework.stereotype.Service;

@Service
public class BusinessServiceModuloBasedFail {

    public int internalCounter = 0;
    public int modulo = 3;

    public String doSomething() {
        internalCounter++;
        if (internalCounter % modulo == 0) {
            throw new RuntimeException("modulo reached, throw successive exception");
        }
        return String.valueOf(internalCounter);
    }

}
