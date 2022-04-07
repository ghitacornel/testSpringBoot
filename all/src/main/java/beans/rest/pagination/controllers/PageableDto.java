package beans.rest.pagination.controllers;

import lombok.Data;

@Data
public class PageableDto {

    private int page;
    private int size = 1;
    private String property;
    private String order;

}
