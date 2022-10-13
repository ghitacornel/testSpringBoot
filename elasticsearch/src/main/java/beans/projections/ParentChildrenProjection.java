package beans.projections;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ParentChildrenProjection {

    private Integer id;
    private String name;
    private String content;
    private List<ChildProjection> children;

}
