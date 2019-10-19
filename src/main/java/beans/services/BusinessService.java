package beans.services;

import org.springframework.stereotype.Component;

@Component
public class BusinessService {

    public String calculateName(String input) {
        return input + input;
    }
}
