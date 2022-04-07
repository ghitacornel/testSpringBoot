package beans.jms.queue.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JMSMessageQueue implements Serializable {

    private int id;
    private String payload;

}
