package docker.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    @NotNull
    @Schema(description = "id of the person", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    @NotBlank
    @Schema(description = "name of the person", example = "John", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotNull
    @Schema(description = "register date", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime registerDate;

}
