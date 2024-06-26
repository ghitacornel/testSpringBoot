package timers.timer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Uncomment commented lines and observe effect to the console<br>
 * Feel free to test with various settings or CRON expressions
 */
@Component
class ScheduledTimer {

    @Scheduled(fixedRate = 1000)
    private void reportCurrentTime() {
        System.out.println(this + " stupid timer was triggered at " + new Date());
    }

}
