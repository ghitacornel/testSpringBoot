package beans.rest.clients.resttemplate.external;

import org.springframework.web.bind.annotation.*;

/**
 * acts as an external to the application REST endpoint provider
 */
@RestController
@RequestMapping("resttemplate")
public class ExternalRestServiceController {

    @PostMapping
    public ExternalRestServiceOutputModel call(@RequestBody ExternalRestServiceInputModel input) {
        ExternalRestServiceOutputModel output = new ExternalRestServiceOutputModel();
        output.setOutput(input.getInput() + input.getInput());
        return output;
    }
}
