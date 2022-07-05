package eu.ensup.gatling.simulation;

import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpDsl;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.OpenInjectionStep.atOnceUsers;

public class UserSimulation extends Simulation {


    ScenarioBuilder login_api =  CoreDsl.scenario("signin")
            .exec(HttpDsl.http("signin")
                    .post("/signin")
                    .body(CoreDsl.StringBody("{ \"username\": \"directeur\", \"password\": \"directeur\" }"))
                    .asJson()
            ).pause(1);

    HttpProtocolBuilder httpProtocol = HttpDsl.http
            .baseUrl("http://127.0.0.1:9999/user-service/auth")
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .acceptEncodingHeader("gzip, deflate")
            .acceptLanguageHeader("it-IT,it;q=0.8,en-US;q=0.5,en;q=0.3")
            .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0");
    ScenarioBuilder directeur = CoreDsl.scenario("Directeur1").exec(login_api);
    ScenarioBuilder directeur2 = CoreDsl.scenario("Directeur2").exec(login_api);

    {
        setUp(
                //permet de tester 10 utilisateur sur 10 sec
                directeur.injectOpen(CoreDsl.rampUsers(10).during(10)),
                directeur2.injectOpen(atOnceUsers(10))
                //permet de tester 10 utilisateur en meme temps
        ).protocols(httpProtocol);
    }
}
