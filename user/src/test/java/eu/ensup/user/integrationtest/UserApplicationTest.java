package eu.ensup.user.integrationtest;

import eu.ensup.user.UserApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplicationTest
{
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
