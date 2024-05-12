package beans.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling
public class ScheduledLogger {

    // task is scheduled to run every 6 seconds
    @Scheduled(cron = "0/6 * * * * *")
    public void scheduledTaskEvery6Seconds() {
        log.info("info message {}", Thread.currentThread());
        log.warn("warn message {}", Thread.currentThread());
        log.error("error message {}", Thread.currentThread());
        log.debug("debug message {}", Thread.currentThread());
        log.trace("trace message {}", Thread.currentThread());
    }
}
