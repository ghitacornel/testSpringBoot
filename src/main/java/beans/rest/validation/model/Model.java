package beans.rest.validation.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Model {

    @NotNull
    private Integer id;

    @NotNull
    @NotEmpty
    private String name;

}
