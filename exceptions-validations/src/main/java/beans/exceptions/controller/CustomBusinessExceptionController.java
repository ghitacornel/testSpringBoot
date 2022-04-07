package beans.exceptions.controller;

import beans.exceptions.service.CustomBusinessExceptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("exceptions")
@RequiredArgsConstructor
public class CustomBusinessExceptionController {

    private final CustomBusinessExceptionService service;

    @GetMapping("executeAndRaiseBusinessException")
    public void executeAndRaiseBusinessException() {
        service.executeAndRaiseBusinessException();
    }

    @GetMapping("executeAndRaiseBusinessExceptionMarked")
    public void executeAndRaiseBusinessExceptionMarked() {
        service.executeAndRaiseBusinessExceptionMarked();
    }

}
