package beans.services;

import org.springframework.stereotype.Service;

@Service
public class BusinessServicePermanentFailOnThreshold {

    public int internalCounter = 0;
    public int threshold = 3;

    public String doSomething() {
        if (threshold < internalCounter) {
            throw new RuntimeException("threshold reached, throw permanent exception");
        }
        internalCounter++;
        return String.valueOf(internalCounter);
    }

}
