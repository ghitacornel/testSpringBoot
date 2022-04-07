package beans.metrics.controller;

import beans.metrics.service.MetricsService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "monitored")
@RequiredArgsConstructor
public class MetricsController {

    private final MetricsService metricsService;

    // invoke a couple of times http://localhost:8081/monitored/service1
    // invoke a couple of times http://localhost:8081/monitored/service2

    // check http://localhost:8081/actuator
    // check http://localhost:8081/actuator/metrics
    // check http://localhost:8081/actuator/metrics/service1.count
    // check http://localhost:8081/actuator/metrics/service1.time
    // check http://localhost:8081/actuator/metrics/service2.count
    // check http://localhost:8081/actuator/metrics/service2.time

    @Counted(value = "service1.count", description = "service1 invocations number")
    @Timed(value = "service1.time", description = "service1 time duration")
    @GetMapping("service1")
    public int service1() {
        return metricsService.service1();
    }

    @Counted(value = "service2.count", description = "service2 invocations number")
    @Timed(value = "service2.time", description = "service2 time duration")
    @GetMapping("service2")
    public int service2() {
        return metricsService.service2();
    }
}
