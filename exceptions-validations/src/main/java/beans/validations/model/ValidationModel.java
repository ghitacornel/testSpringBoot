package beans.validations.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

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
