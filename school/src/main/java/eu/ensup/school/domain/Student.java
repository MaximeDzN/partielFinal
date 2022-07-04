package eu.ensup.school.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student extends GenericEntity{

    String firstName;
    String lastName;
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date birthDate;
    String mail;
    String phoneNumber;
    String address;

    @ManyToMany
    List<Course> courses = new ArrayList<>();

}
