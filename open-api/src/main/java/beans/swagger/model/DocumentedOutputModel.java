package beans.swagger.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(description = "documented output model description")
@Data
public class DocumentedOutputModel {

    @Schema(description = "Unique identifier of the output Model", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @Positive
    @NotNull
    private Integer id;

    @Schema(description = "Description of the output Model", example = "abc", requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(min = 50)
    @NotEmpty
    private String description;

}
