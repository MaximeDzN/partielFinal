package eu.ensup.gateway.config;

import eu.ensup.gateway.filter.JwtAuthFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private JwtAuthFilter jwtAuthFilter;

    public GatewayConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }


    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("firstService",r -> r.path("/fs/**").filters(f -> f.filter(jwtAuthFilter)).uri("lb://ARTIST-SERVICE"))
                .route("authId",r -> r.path("/secure/**").filters(f -> f.filter(jwtAuthFilter)).uri("lb://AUTH-SERVICE"))
                .build();
    }
}
