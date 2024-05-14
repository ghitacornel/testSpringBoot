package beans.controller;

import beans.model.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/format")
@RestController
public class DateTimeTestController {

    @PostMapping
    public Model post(@RequestBody Model model) {
        log.info(model.toString());
        return Model.builder()
                .name(model.getName() + " altered")
                .localDate(model.getLocalDate().plusDays(1))
                .localDateTime(model.getLocalDateTime().plusDays(1))
                .localTime(model.getLocalTime().plusHours(1))
                .build();
    }

}
