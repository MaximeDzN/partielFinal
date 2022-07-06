package eu.ensup.school.repository;

import eu.ensup.school.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CourseRepository extends JpaRepository<Course,Long> {
    long countCourseByStudentsIsNull();
}
