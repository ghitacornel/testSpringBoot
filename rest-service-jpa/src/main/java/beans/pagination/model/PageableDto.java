package beans.pagination.model;

import lombok.Data;

@Data
public class PageableDto {

    private int page;
    private int size = 1;
    private String property;
    private String order;

}
