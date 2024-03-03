package docker.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePersonDto {

    @Schema(description = "name of person", example = "John", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "date of birth of person", defaultValue = "2024-03-04", example = "2024-03-04", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dateOfBirth;

}
