package eu.ensup.school.repository;

import eu.ensup.school.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
