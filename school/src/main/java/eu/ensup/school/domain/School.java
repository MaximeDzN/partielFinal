package eu.ensup.school.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class School extends GenericEntity{

    String name;
    String mail;
    String address;
    String phoneNumber;
    Long directorId;

}
