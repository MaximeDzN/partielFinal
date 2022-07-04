package eu.ensup.school.integrationtest;

import eu.ensup.school.SchoolApplication;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = { SchoolApplication.class })
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseIntegrationTest
{
    @Test
    public void shouldRetrieveAnEventById() {
        String body = RestAssured.
                given().
                accept(ContentType.JSON).
                when().
                get("/api/entities/blah").
                then().
                statusCode(HttpStatus.SC_OK).
                contentType(ContentType.JSON).
                extract().asString();

        MatcherAssert.assertThat(body, Matchers.equalTo("Retrieved entity with id: blah"));
    }
}
