package eu.ensup.school.integrationtest;

import eu.ensup.school.SchoolApplication;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SchoolApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseControllerWIT
{
    @LocalServerPort
    private int port;

    private String host = "http://localhost:8080";

    @BeforeEach
    public void configure()
    {
        host = "http://localhost:"+port;
    }

    @Test
    @Order(1)
    void countAll()
    {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<Long> response = testRestTemplate.getForEntity(host+"/courses/count/all", Long.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        MatcherAssert.assertThat(response.getBody(), Matchers.equalTo(9L));
    }

    @Test
    @Order(2)
    void associateStudentCourseSuccess()
    {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<Long> response = testRestTemplate.getForEntity(host+"/courses/associate/1/1", Long.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        MatcherAssert.assertThat(response.getStatusCode().is2xxSuccessful(), Matchers.equalTo(true));
    }

    @Test
    @Order(3)
    void associateStudentCourse()
    {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<Long> response = testRestTemplate.getForEntity(host+"/courses/associate/20/20", Long.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        MatcherAssert.assertThat(response.getStatusCode().is2xxSuccessful(), Matchers.equalTo(true));
    }

    @Test
    @Order(4)
    void countCourseWithoutStudents()
    {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<Long> response = testRestTemplate.getForEntity(host+"/courses/count/courseWithoutStudents", Long.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        MatcherAssert.assertThat(response.getBody(), Matchers.equalTo(7L));
    }
}