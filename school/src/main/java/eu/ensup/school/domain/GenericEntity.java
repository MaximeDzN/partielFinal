package eu.ensup.school.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date createdAt;
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date modifiedAt;

    @PrePersist
    public void prePersist(){
        Date d = new Date();
        this.createdAt = d;
        this.modifiedAt = d;
    }

    @PreUpdate
    public void preUpdate(){
        this.modifiedAt = new Date();
    }

}