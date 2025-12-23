package beans.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "simple")
class SimpleController {

    @GetMapping
    public String testSimple() {
        return "all good " + Thread.currentThread().getName();
    }

    @GetMapping("delay")
    public String testSimpleWithLongDelay() {

        log.info("delaying thread {}", Thread.currentThread().getName());

        //just sleep
        try {
            Thread.currentThread().sleep(10 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("delayed thread {}", Thread.currentThread().getName());

        return "all good with delay for thread " + Thread.currentThread().getName();
    }

}
