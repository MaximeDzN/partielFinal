package eu.ensup.school.repository;

import eu.ensup.school.domain.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SchoolRepository extends JpaRepository<School,Long> {
}
