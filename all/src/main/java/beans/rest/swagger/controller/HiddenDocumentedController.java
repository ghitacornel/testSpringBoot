package beans.rest.swagger.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden// do not expose it via Swagger
@OpenAPIDefinition(info = @Info(title = "Custom REST Documentation title", description = "Custom Documented Hidden Controller description", version = "1"))
@RestController
@RequestMapping(value = "documented/hidded")
public class HiddenDocumentedController {

    @GetMapping
    public String getRequest() {
        return "this is hidden in Swagger";
    }

}
