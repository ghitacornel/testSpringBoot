package beans.rest.jpa.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Item")
public class Item {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "length")
    private Integer length;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "registration")
    private LocalDateTime registration;

    @Column(name = "fake")
    private Boolean fake;

    @Enumerated
    @Column(name = "state")
    private State state;

    public enum State {
        NEW, USED, RETIRED
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public LocalDateTime getRegistration() {
        return registration;
    }

    public void setRegistration(LocalDateTime registration) {
        this.registration = registration;
    }

    public Boolean getFake() {
        return fake;
    }

    public void setFake(Boolean fake) {
        this.fake = fake;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", length=" + length +
                ", weight=" + weight +
                ", registration=" + registration +
                ", fake=" + fake +
                ", state=" + state +
                '}';
    }

    public interface ItemProjection {
        Integer getId();

        String getName();
    }
}
