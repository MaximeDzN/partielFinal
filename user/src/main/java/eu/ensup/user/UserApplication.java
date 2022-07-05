package eu.ensup.user;

import eu.ensup.user.config.PasswordConfig;
import eu.ensup.user.domain.User;
import eu.ensup.user.domain.enums.Role;
import eu.ensup.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class UserApplication {


    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Bean
    CommandLineRunner start(UserRepository userRepository, PasswordConfig passwordConfig, RepositoryRestConfiguration repositoryRestConfiguration){
        return args -> {
            repositoryRestConfiguration.exposeIdsFor(User.class);
            userRepository.save(User.builder().username("directeur").password(passwordConfig.passwordEncoder().encode("directeur")).role(Role.ROLE_DIRECTOR).build());
            userRepository.save(User.builder().username("responsable").password(passwordConfig.passwordEncoder().encode("responsable")).role(Role.ROLE_RESPONSABLE).build());

        };
    }

}
