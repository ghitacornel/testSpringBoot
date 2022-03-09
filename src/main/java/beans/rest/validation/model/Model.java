package beans.rest.validation.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
public class Model {

    @Positive
    @NotNull
    private Integer id;

    @Size(min = 2, max = 30)
    @NotEmpty
    private String name;

}
