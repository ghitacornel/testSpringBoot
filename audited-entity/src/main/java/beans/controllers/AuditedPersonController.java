package beans.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("audit")
@RestController
@RequiredArgsConstructor
class AuditedPersonController {

    private final AuditedPersonControllerService service;

    @PostMapping
    PersonResponse createDummy() {
        return service.createDummy();
    }

    @PatchMapping
    PersonResponse updateDummy() {
        return service.updateDummy();
    }

    @GetMapping
    PersonResponse getDummy() {
        return service.getDummy();
    }
}
