package beans.pagination.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageableDto {

    private int page;
    private int size = 1;
    private String property;
    private String order;

}
