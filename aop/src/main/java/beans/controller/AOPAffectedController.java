package beans.controller;

import beans.aop.LogExecution;
import beans.aop.LogExecutionTime;
import beans.service.AOPAffectedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("aop")
@LogExecution
@RequiredArgsConstructor
public class AOPAffectedController {

    private final AOPAffectedService service;

    @GetMapping("method1")
    public String method1() {
        return service.method1();
    }

    @LogExecutionTime
    @GetMapping("method2")
    public String method2() {
        return service.method2();
    }
}
