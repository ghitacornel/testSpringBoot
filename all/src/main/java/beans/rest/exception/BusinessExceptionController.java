package beans.rest.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("exceptions")
@RequiredArgsConstructor
public class BusinessExceptionController {

    private final BusinessExceptionService service;

    @GetMapping("executeAndRaiseBusinessException")
    public void executeAndRaiseBusinessException() {
        service.executeAndRaiseBusinessException();
    }

    @GetMapping("executeAndRaiseBusinessExceptionMarked")
    public void executeAndRaiseBusinessExceptionMarked() {
        service.executeAndRaiseBusinessExceptionMarked();
    }

}
