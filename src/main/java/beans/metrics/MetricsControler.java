package beans.metrics;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "monitored")
@RequiredArgsConstructor
public class MetricsControler {

    private final MetricsService metricsService;

    // invoke a couple of times http://localhost:8081/monitored/count
    // invoke a couple of times http://localhost:8081/monitored/duration
    // check http://localhost:8081/actuator
    // check http://localhost:8081/actuator/metrics
    // check http://localhost:8081/actuator/metrics/count.number
    // check http://localhost:8081/actuator/metrics/count.time
    // check http://localhost:8081/actuator/metrics/duration.number
    // check http://localhost:8081/actuator/metrics/duration.time

    @Counted(value = "count.number", description = "invokeForCount invocations number")
    @Timed(value = "count.time", description = "invokeForCount time duration")
    @GetMapping("count")
    public int invokeForCount() {
        return metricsService.count();
    }

    @Counted(value = "duration.number", description = "invokeForDuration invocations number")
    @Timed(value = "duration.time", description = "invokeForDuration time duration")
    @GetMapping("duration")
    public int invokeForDuration() {
        return metricsService.duration();
    }
}
