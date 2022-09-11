package beans.controller.model;

import lombok.Data;

@Data
public class CreateItemRequest {

    private String name;
    private int quantity;
    private Double price;

}
