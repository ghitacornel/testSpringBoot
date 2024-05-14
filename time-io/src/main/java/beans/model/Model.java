package beans.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Model {

    private String name;
    private LocalDate localDate;
    private LocalDateTime localDateTime;
    private LocalTime localTime;// doesn't work

}
