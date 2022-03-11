package beans.rest.swagger.controller;

import beans.rest.swagger.model.InputModel;
import beans.rest.swagger.model.OutputModel;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

@OpenAPIDefinition(info = @Info(title = "Custom REST Documentation title", description = "Custom Documented Controller description", version = "1"))
@RestController
@RequestMapping(value = "documented")
public class DocumentedController {
//
//    @Operation(summary = "GET request documentation", description = "full documentation of this GET request")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "all good on POST"),
//            @ApiResponse(responseCode = "400", description = "Bad boy GET request")
//    }
//    )
//    @GetMapping(value = "{id}")
//    public String getRequest(@Parameter(description = "Documented Model used as input for GET") @PathVariable(name = "id") Integer id) {
//        return "GET an OK for this " + id;
//    }
//
//    @Operation(summary = "DELETE request documentation", description = "full documentation of this DELETE request")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "all good on DELETE"),
//            @ApiResponse(responseCode = "400", description = "Bad boy DELETE request")
//    }
//    )
//    @DeleteMapping(value = "/{id}")
//    public String deleteRequest(@Parameter(description = "Documented Model used as input for DELETE") @RequestParam(name = "id") Integer id) {
//        return "GET an OK for this " + id;
//    }
//
//    @Operation(summary = "POST request documentation", description = "full documentation of this POST request")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "all good on POST"),
//            @ApiResponse(responseCode = "400", description = "Bad boy POST request")
//    }
//    )
//    @PostMapping(value = "postRequest")
//    public OutputModel postRequest(@Parameter(description = "Documented Model used as input for POST") @RequestBody InputModel inputModel) {
//        return new OutputModel();
//    }
//
//    @Operation(summary = "PUT request documentation", description = "full documentation of this PUT request")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "all good on PUT"),
//            @ApiResponse(responseCode = "400", description = "Bad boy PUT request")
//    }
//    )
//    @PutMapping(value = "putRequest")
//    public OutputModel putRequest(@Parameter(description = "Documented Model used as input for PUT", required = true) @RequestBody InputModel inputModel) {
//        return new OutputModel();
//    }

}
