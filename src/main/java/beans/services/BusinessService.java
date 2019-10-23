package beans.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    @Autowired
    BusinessConfiguration businessConfiguration;

    public String calculateName(String input) {
        return input + " " + businessConfiguration.getSetting();
    }
}
