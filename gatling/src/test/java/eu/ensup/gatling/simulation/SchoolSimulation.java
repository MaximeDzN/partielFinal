package eu.ensup.gatling.simulation;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.Http;
import io.gatling.javaapi.http.HttpDsl;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import static io.gatling.javaapi.core.OpenInjectionStep.atOnceUsers;

public class SchoolSimulation extends Simulation {


    //Load properties file

    String url = "http://"+System.getProperty("IpAdress")+":8099";
//    String url = "http://18.216.190.197:8099";

    Map<String,String> values = new HashMap<String, String>() {{
        put("username", "directeur");
        put ("password", "directeur");
    }};
    ObjectMapper objectMapper = new ObjectMapper();

    String rbody;
    String token = "Bearer ";
    {
        try {
            rbody = objectMapper.writeValueAsString(values);
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url+"/user-service/auth/signin"))
                    .POST(HttpRequest.BodyPublishers.ofString(rbody))
                    .setHeader("Content-type","application/json")
                    .build();
            HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
            JSONObject jsonTk = new JSONObject(response.body());
            token= token+ jsonTk.getString("token");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    ScenarioBuilder getAll_student_api =  CoreDsl.scenario("getall_students")
            .exec(HttpDsl.http("students_getall")
                    .get("/students").header("Authorization",token)
            ).pause(1);

    ScenarioBuilder getAll_courses_api =  CoreDsl.scenario("getall_courses")
            .exec(HttpDsl.http("courses_getall")
                    .get("/courses").header("Authorization",token)
            ).pause(1);

    HttpProtocolBuilder httpProtocol = HttpDsl.http
            .baseUrl(url+"/school-service")
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .acceptEncodingHeader("gzip, deflate")
            .acceptLanguageHeader("it-IT,it;q=0.8,en-US;q=0.5,en;q=0.3")
            .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0");
    ScenarioBuilder directeur = CoreDsl.scenario("Directeur1").exec(getAll_student_api,getAll_courses_api);
    ScenarioBuilder directeur2 = CoreDsl.scenario("Directeur2").exec(getAll_student_api,getAll_courses_api);

    {
        setUp(
                //permet de tester 10 utilisateur sur 10 sec
                directeur.injectOpen(CoreDsl.rampUsers(100).during(10)),
                directeur2.injectOpen(atOnceUsers(100))
                //permet de tester 10 utilisateur en meme temps
        ).protocols(httpProtocol);
    }

}
