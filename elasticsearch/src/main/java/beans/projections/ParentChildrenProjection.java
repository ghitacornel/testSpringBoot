package beans.projections;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParentChildrenProjection {

    private Integer id;
    private String name;
    private String content;

}
