package timers.timer;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.Date;

@Component
@RequiredArgsConstructor
class ProgrammaticTimer {

    private final TaskScheduler scheduler;

    // starter
    @PostConstruct
    private void triggerScheduledMethod() {
        scheduler.schedule(this::programmaticScheduledMethod, Instant.now().plusSeconds(5));
    }

    private void programmaticScheduledMethod() {
        System.out.println(this + " programmatic timer was triggered at " + new Date());
    }

}
