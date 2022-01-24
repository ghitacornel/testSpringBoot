package beans.metrics;

import org.springframework.stereotype.Service;

@Service
public class MetricsService {

    public int noCount;
    public int noDuration;

    public int count() {
        noCount++;
        return noCount;
    }

    public int duration() {
        noDuration++;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException("wait failed", e);
        }
        return noDuration;
    }
}
