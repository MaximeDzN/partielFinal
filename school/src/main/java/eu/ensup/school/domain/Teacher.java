package eu.ensup.school.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher extends GenericEntity{

    String firstName;
    String lastName;
    String mail;
    String phoneNumber;
    String address;
    String subject;

}
