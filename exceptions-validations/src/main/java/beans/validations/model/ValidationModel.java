package beans.validations.model;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Data
@Entity
public class ValidationModel {

    @Id
    @Positive
    @NotNull
    private Integer id;

    @Size(min = 2, max = 30)
    @NotEmpty
    private String name;

}
