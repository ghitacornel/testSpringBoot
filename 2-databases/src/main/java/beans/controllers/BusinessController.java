package beans.controllers;

import beans.services.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("play")
public class BusinessController {

    private final BusinessService service;

    @GetMapping
    public String play() {
        service.play();
        return "OK";
    }

}
