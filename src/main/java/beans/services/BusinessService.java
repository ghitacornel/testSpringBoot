package beans.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BusinessService {

    @Value("${my.custom.setting}")
    private String setting;

    @PostConstruct
    public void init(){
        System.err.println(setting);
    }

    public String calculateName(String input) {
        return input + input;
    }
}
