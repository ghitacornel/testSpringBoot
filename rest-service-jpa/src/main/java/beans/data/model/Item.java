package beans.data.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

// used for testing methods provided out of the box by Spring Data Repository
@Entity
@Getter
@Setter
public class Item {

    @Id
    private Integer id;

    private String name;
    private Integer length;
    private Double weight;
    private LocalDateTime registration;
    private Boolean fake;

    @Enumerated(EnumType.STRING)
    private State state;

    public enum State {
        NEW, USED, RETIRED
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Item item = (Item) o;
        return id != null && Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
