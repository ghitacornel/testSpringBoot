package beans.rest.validation.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Model {

    @NotNull
    private Integer id;

    @NotEmpty
    private String name;

    public Model(@NotNull Integer id, @NotEmpty String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
