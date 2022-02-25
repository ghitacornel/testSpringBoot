package beans.rest.retry.controller;

import beans.rest.retry.service.RetryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "retry")
@RequiredArgsConstructor
public class RetryController {

    private final RetryService retryService;

    @GetMapping("stableResource")
    public String stableResource() {
        return retryService.stableResource();
    }

    @GetMapping("resourceFailBasedOnParameter/{parameter}")
    public String resourceFailBasedOnParameter(@PathVariable(name = "parameter") Boolean parameter) {
        return retryService.resourceFailBasedOnParameter(parameter);
    }
}
