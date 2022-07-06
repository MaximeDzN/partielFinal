package eu.ensup.school.integrationtest;

import eu.ensup.school.SchoolApplicationTest;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SchoolApplicationTest.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseControllerWIT
{
    @LocalServerPort
    private int port;

    private String host = "http://localhost:8080/school-service";

    @BeforeEach
    public void configure()
    {
        host = "http://localhost:"+port+"";
    }

    @Test
    void test()
    {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<Long> response = testRestTemplate.getForEntity(host+"/courses/count/all", Long.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        MatcherAssert.assertThat(response.getBody(), Matchers.equalTo(9L));
    }
}