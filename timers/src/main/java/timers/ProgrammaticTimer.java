package timers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.Date;

@Component
public class ProgrammaticTimer {

    @Autowired
    private TaskScheduler scheduler;

    // starter
    @PostConstruct
    public void reportCurrentTime() {
        scheduler.schedule(this::programmaticScheduledMethod, Instant.now().plusSeconds(5));
    }

    private void programmaticScheduledMethod() {
        System.out.println(this + " programmatic timer was triggered at " + new Date());
    }

}
