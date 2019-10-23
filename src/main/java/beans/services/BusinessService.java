package beans.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BusinessService {

    @Value("${my.custom.setting}")
    private String setting;

    public String calculateName(String input) {
        return input + " " + setting;
    }
}
