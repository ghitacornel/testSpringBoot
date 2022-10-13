package beans.projections;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChildProjection {

    private Integer id;
    private String name;
    private String content;

}
