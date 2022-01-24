package beans.metrics;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/monitored")
@RequiredArgsConstructor
public class MetricsControler {

    private final MetricsService metricsService;

    @Counted(value = "count.number", description = "invokeForCount invocations number")
    @Timed(value = "count.time", description = "invokeForCount time duration")
    @GetMapping("/count")
    public int invokeForCount() {
        return metricsService.count();
    }

    @Counted(value = "duration.number", description = "invokeForDuration invocations number")
    @Timed(value = "duration.time", description = "invokeForDuration time duration")
    @GetMapping("/duration")
    public int invokeForDuration() {
        return metricsService.duration();
    }
}
