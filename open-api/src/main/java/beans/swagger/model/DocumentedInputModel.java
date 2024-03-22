package beans.swagger.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(description = "documented input model description")
@Data
public class DocumentedInputModel {

    @Schema(description = "Unique identifier of the input Model", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @Positive
    @NotNull
    private Integer id;

    @Schema(description = "Name of the input Model", example = "abc", requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(min = 2, max = 30)
    @NotBlank
    private String name;

}
