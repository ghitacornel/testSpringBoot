package beans.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InputModel {

    private int id;
    private String name;

}
