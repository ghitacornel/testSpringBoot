package beans.rest.validation.controller;

import beans.rest.validation.model.Model;
import beans.rest.validation.service.ValidationService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@OpenAPIDefinition(info = @Info(title = "Validation", description = "Controller used for validation", version = "1"))
@RestController
@RequestMapping(value = "validate")
@RequiredArgsConstructor
public class ValidationController {

    private final ValidationService service;

    @Operation(summary = "REST layer validation", description = "validation performed automatically in REST layer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "validation succeeded"),
            @ApiResponse(responseCode = "400", description = "Bad request, validation failed")
    }
    )
    @PutMapping(value = "rest")
    public Model invokeRest(@Parameter(description = "Model to validate in REST") @Valid @RequestBody Model model) {
        return model;
    }

    @Operation(summary = "Service layer validation", description = "validation performed automatically in Service layer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "validation succeeded"),
            @ApiResponse(responseCode = "400", description = "Bad request, validation failed")
    }
    )
    @PutMapping(value = "service")
    public Model invokeService(@Parameter(description = "Model to validate in Service", required = true) @RequestBody Model model) {
        return service.invoke(model);
    }

}
