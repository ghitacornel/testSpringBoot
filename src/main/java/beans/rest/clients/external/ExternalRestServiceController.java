package beans.rest.clients.external;

import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * acts as an external to the application REST endpoint provider
 */
@RestController
@RequestMapping("externalService")
public class ExternalRestServiceController {

    @GetMapping("{input}")
    public String callGet(@PathParam("input") String input) {
        return input + " + added by external client";
    }

    @PostMapping
    public ExternalRestServiceOutputModel callPost(@RequestBody ExternalRestServiceInputModel input) {
        ExternalRestServiceOutputModel output = new ExternalRestServiceOutputModel();
        output.setOutput(input.getInput() + " + added by external client");
        return output;
    }
}
