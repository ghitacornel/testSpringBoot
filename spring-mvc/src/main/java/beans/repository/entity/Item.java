package beans.repository.entity;

import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Min(1)
    private int quantity;

    @NotNull
    private Double price;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;

}
