package beans.swagger.controller;

import beans.swagger.model.DocumentedInputModel;
import beans.swagger.model.DocumentedOutputModel;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "documented")
public class DocumentedController {

    @Operation(summary = "GET request documentation", description = "full documentation of this GET request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "all good on POST"),
            @ApiResponse(responseCode = "400", description = "Bad boy GET request")
    }
    )
    @GetMapping(value = "{id}")
    public String getRequest(@Parameter(description = "Documented Model used as input for GET") @PathVariable(name = "id") Integer id) {
        return "GET an OK for this " + id;
    }

    @Hidden
    @GetMapping(value = "/hidden")
    public String getRequest() {
        return "Rest endpoint not exposed on Swagger";
    }

    @Operation(summary = "DELETE request documentation", description = "full documentation of this DELETE request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "all good on DELETE"),
            @ApiResponse(responseCode = "400", description = "Bad boy DELETE request")
    }
    )
    @DeleteMapping
    public String deleteRequest(@Parameter(description = "Documented Model used as input for DELETE") @RequestParam(name = "id") Integer id) {
        return "GET an OK for this " + id;
    }

    @Operation(summary = "POST request documentation", description = "full documentation of this POST request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "all good on POST"),
            @ApiResponse(responseCode = "400", description = "Bad boy POST request")
    }
    )
    @PostMapping
    public DocumentedOutputModel postRequest(@Parameter(description = "Documented Model used as input for POST") @RequestBody DocumentedInputModel documentedInputModel) {
        return new DocumentedOutputModel();
    }

    @Operation(summary = "PUT request documentation", description = "full documentation of this PUT request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "all good on PUT"),
            @ApiResponse(responseCode = "400", description = "Bad boy PUT request")
    }
    )
    @PutMapping
    public DocumentedOutputModel putRequest(@Parameter(description = "Documented Model used as input for PUT", required = true) @RequestBody DocumentedInputModel documentedInputModel) {
        return new DocumentedOutputModel();
    }

}
