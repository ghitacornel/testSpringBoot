package shed.timer;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TimerComponent {

    // task is scheduled to run every 6 seconds
    @Scheduled(cron = "0/6 * * * * *")
    // lock prevents the previous scheduling rule, it keeps the lock for at least 10 seconds and at most 15 seconds
    @SchedulerLock(name = "scheduledTaskName", lockAtLeastFor = "PT10S", lockAtMostFor = "PT15S")
    public void scheduledTaskEvery6Seconds() {
        log.info("scheduled task executed by {}", Thread.currentThread());
    }

}
