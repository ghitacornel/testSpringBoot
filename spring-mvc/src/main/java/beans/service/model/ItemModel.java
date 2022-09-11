package beans.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemModel {

    private Integer id;
    private String name;

    @Min(1)
    private int quantity;

    @NotNull
    private Double price;

    private Date registerDate;
}
