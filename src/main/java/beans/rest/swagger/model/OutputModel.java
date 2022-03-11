package beans.rest.swagger.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Schema(description = "documented output model description")
@Data
public class OutputModel {

    @Schema(description = "Unique identifier of the output Model", example = "1", required = true)
    @Positive
    @NotNull
    private Integer id;

    @Schema(description = "Description of the output Model", example = "abc", required = true)
    @Size(min = 50)
    @NotEmpty
    private String description;

}
