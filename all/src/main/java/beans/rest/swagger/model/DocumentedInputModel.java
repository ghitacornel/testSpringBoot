package beans.rest.swagger.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Schema(description = "documented input model description")
@Data
public class DocumentedInputModel {

    @Schema(description = "Unique identifier of the input Model", example = "1", required = true)
    @Positive
    @NotNull
    private Integer id;

    @Schema(description = "Name of the input Model", example = "abc", required = true)
    @Size(min = 2, max = 30)
    @NotBlank
    private String name;

}