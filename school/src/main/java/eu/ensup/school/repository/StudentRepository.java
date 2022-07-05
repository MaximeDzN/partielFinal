package eu.ensup.school.repository;

import eu.ensup.school.domain.Course;
import eu.ensup.school.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StudentRepository extends JpaRepository<Student,Long> {
    long countStudentByCoursesIsNull();
    long countStudentByCourses(Course course);
}
