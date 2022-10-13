package beans.projections;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParentProjection {

    private Integer id;
    private String name;
    private String content;

}
