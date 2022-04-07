package http.interceptors.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("intercepted")
public class InterceptedController {

    @GetMapping
    public String doSomething() {
        return "doSomething";
    }
}
