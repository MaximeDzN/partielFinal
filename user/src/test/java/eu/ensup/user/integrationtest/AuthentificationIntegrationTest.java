package eu.ensup.user.integrationtest;

import eu.ensup.user.UserApplication;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = { UserApplication.class })
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthentificationIntegrationTest
{
    @Test
    public void shouldRetrieveAnEventById() {
        String body = RestAssured.
                given().
                accept(ContentType.TEXT).
                body("username=test;password=test;").
                when().
                get("/signup").
                then().
                statusCode(HttpStatus.SC_OK).
                contentType(ContentType.ANY).
                extract().asString();

        MatcherAssert.assertThat(body, Matchers.equalTo("Retrieved entity with id: blah"));
    }
}
