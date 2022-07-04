package eu.ensup.school.domain;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    String theme;
    int hours;

//    Course(Long id, Date createdAt, Date updatedAt,String theme, int hours){
//        super(id,createdAt,updatedAt);
//        this.theme = theme;
//        this.hours = hours;
//    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students = new ArrayList<>();

    public <E> Course(long l, Date date, Date date1, String math, int i, ArrayList<E> es) {
    }
}
