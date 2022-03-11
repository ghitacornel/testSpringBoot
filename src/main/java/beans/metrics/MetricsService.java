package beans.metrics;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MetricsService {

    public int service1Count;
    public int service2Count;

    public int service1() {
        service1Count++;
        try {
            Thread.sleep(new Random().nextInt(100));
        } catch (InterruptedException e) {
            throw new RuntimeException("wait failed", e);
        }
        return service1Count;
    }

    public int service2() {
        service2Count++;
        try {
            Thread.sleep(new Random().nextInt(100));
        } catch (InterruptedException e) {
            throw new RuntimeException("wait failed", e);
        }
        return service2Count;
    }
}
