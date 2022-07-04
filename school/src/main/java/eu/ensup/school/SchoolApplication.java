package eu.ensup.school;

import com.github.javafaker.Faker;
import eu.ensup.school.domain.Course;
import eu.ensup.school.domain.School;
import eu.ensup.school.domain.Student;
import eu.ensup.school.repository.CourseRepository;
import eu.ensup.school.repository.SchoolRepository;
import eu.ensup.school.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class SchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CourseRepository courseRepository, StudentRepository studentRepository, SchoolRepository schoolRepository, RepositoryRestConfiguration repositoryRestConfiguration){
		return args -> {
			Faker faker = new Faker();
			repositoryRestConfiguration.exposeIdsFor(Course.class);
			courseRepository.save(Course.builder().theme("Mathématique").hours(100).build());
			courseRepository.save(Course.builder().theme("Informatique").hours(200).build());
			courseRepository.save(Course.builder().theme("Droit").hours(25).build());
			courseRepository.save(Course.builder().theme("Anglais").hours(4000).build());
			courseRepository.save(Course.builder().theme("Espagnol").hours(4).build());
			courseRepository.save(Course.builder().theme("Comptabilité").hours(75).build());
			schoolRepository.save(School.builder()
					.name("ENSITECH")
					.address("10 rue martela, Guyancourt,78XXX")
					.mail("ecole.ensup.eu")
					.phoneNumber("0606060606")
					.directorId(1L)
					.build());
			for (int i = 0; i < 15; i++) {
				String firstname = faker.name().firstName();
				String lastName = faker.name().lastName();
				studentRepository.save(Student.builder()
						.firstName(firstname)
						.lastName(lastName)
						.birthDate(faker.date().birthday())
						.phoneNumber(faker.phoneNumber().phoneNumber())
						.mail(firstname+"."+lastName+"@ensup.eu")
						.address(faker.address().fullAddress())
						.build());
			}

		};
	}

}
