package beans.rest.pagination.controllers;

import lombok.Data;

@Data
public class SortDto {

    private String property;
    private String order;

}
