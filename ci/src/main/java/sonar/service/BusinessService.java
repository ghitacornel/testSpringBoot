package sonar.service;

import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    public int sum(int x, int y) {
        return x + y;
    }
}
