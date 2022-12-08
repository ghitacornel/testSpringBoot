package beans.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = {"createdDate", "modifiedDate", "createdBy", "modifiedBy"})
@EntityListeners(AuditingEntityListener.class)
@ToString
public class Person {

    @Id
    private Integer id;

    private String name;

    private Double salary;

    @Enumerated(EnumType.STRING)
    private Status status = Status.AVAILABLE;

    @CreatedDate
    private long createdDate;

    @LastModifiedDate
    private long modifiedDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String modifiedBy;

}
