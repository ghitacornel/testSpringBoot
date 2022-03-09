package beans.rest.validation.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
public class Model {

    @Schema(description = "Unique identifier of the Model.", example = "1", required = true)
    @Positive
    @NotNull
    private Integer id;

    @Schema(description = "Name of the Model.", example = "abc", required = true)
    @Size(min = 2, max = 30)
    @NotEmpty
    private String name;

}
