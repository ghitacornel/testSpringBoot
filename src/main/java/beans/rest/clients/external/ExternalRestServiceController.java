package beans.rest.clients.external;

import org.springframework.web.bind.annotation.*;

/**
 * acts as an external to the application REST endpoint provider
 */
@RestController
@RequestMapping("externalService")
public class ExternalRestServiceController {

    @GetMapping
    public String callGet() {
        return "some data from external service";
    }

    @PostMapping
    public ExternalRestServiceOutputModel callPost(@RequestBody ExternalRestServiceInputModel input) {
        ExternalRestServiceOutputModel output = new ExternalRestServiceOutputModel();
        output.setOutput(input.getInput() + input.getInput());
        return output;
    }
}
