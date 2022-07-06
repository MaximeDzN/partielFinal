package eu.ensup.school.integrationtest;

import eu.ensup.school.SchoolApplication;
import eu.ensup.school.SchoolApplicationTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SchoolApplication.class)
@SpringBootTest
public class SchoolApplicationIT
{
    @Test
    void contextLoads() {}
}
