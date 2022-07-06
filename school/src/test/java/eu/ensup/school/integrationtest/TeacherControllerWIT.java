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
@SpringBootTest(classes = SchoolApplicationTest.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeacherControllerWIT
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
    public void countAll()
    {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<Long> response = testRestTemplate.getForEntity(host+"/teachers/count/all", Long.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        MatcherAssert.assertThat(response.getBody(), Matchers.equalTo(1L));
    }
}
