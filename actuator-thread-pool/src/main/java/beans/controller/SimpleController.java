package beans.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "simple")
class SimpleController {

    @GetMapping
    public String testSimple() {
        return "all good";
    }

}
