package eu.ensup.school.integrationtest;

import eu.ensup.school.SchoolApplicationTest;
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
@SpringBootTest(classes = SchoolApplicationTest.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class StudentControllerWIT
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
        ResponseEntity<Long> response = testRestTemplate.getForEntity(host+"/students/count/all", Long.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        MatcherAssert.assertThat(response.getBody(), Matchers.equalTo(17L));
    }

    @Test
    @Order(2)
    void studentWithoutCourse()
    {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<Long> response = testRestTemplate.getForEntity(host+"/students/count/studentWithoutCourse", Long.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        MatcherAssert.assertThat(response.getBody(), Matchers.equalTo(16L));
    }

    @Test
    @Order(3)
    void studentsInCourse()
    {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<Long> response = testRestTemplate.getForEntity(host+"/students/count/studentsInCourse/1", Long.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        MatcherAssert.assertThat(response.getBody(), Matchers.equalTo(0L));
    }
}
