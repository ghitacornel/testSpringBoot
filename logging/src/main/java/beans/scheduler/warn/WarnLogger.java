package beans.scheduler.warn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class WarnLogger {

    // log all types every second
    @Scheduled(cron = "0/1 * * * * *")
    public void log() {

        // log all types
        log.info("info message {}", this);
        log.warn("warn message {}", this);
        log.error("error message {}", this);
        log.debug("debug message {}", this);
        log.trace("trace message {}", this);

    }

}
