package beans.validations.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Data
@Entity
public class ValidationModel {

    @Id
    @Positive
    @NotNull
    private Integer id;

    @Size(min = 2, max = 30)
    @NotBlank
    private String name;

}
