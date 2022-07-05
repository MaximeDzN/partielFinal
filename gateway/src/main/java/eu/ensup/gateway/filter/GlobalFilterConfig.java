package eu.ensup.gateway.filter;

import eu.ensup.gateway.security.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@Component
@Order(-1)
public class GlobalFilterConfig implements GlobalFilter {

    final Logger logger = LoggerFactory.getLogger(GlobalFilterConfig.class);

    private JwtUtil jwtUtil;

    public GlobalFilterConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        final List<String> openEndpoints = List.of("signin","signup");
        Predicate<ServerHttpRequest> isApiSecured = r -> openEndpoints.stream().noneMatch(uri -> r.getURI().getPath().contains(uri));
        if(isApiSecured.test(request)){
            if(!request.getHeaders().containsKey("Authorization")){
                logger.info("Not authorized");
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
        }
        if(!request.getHeaders().getOrEmpty("Authorization").isEmpty()){
            final String token = getJwtFromRequest(request);
            if(!jwtUtil.validateToken(token)){
                logger.info("Invalid token");
                response.setStatusCode(HttpStatus.BAD_REQUEST);
                return response.setComplete();
            }
        }

        return chain.filter(exchange);
    }


    private String getJwtFromRequest(ServerHttpRequest request) {
        String namespace = "Bearer ";
        final String authHeader = request.getHeaders().getOrEmpty("Authorization").get(0);
        if (StringUtils.hasText(authHeader) && authHeader.startsWith(namespace)) {
            return authHeader.substring(namespace.length());
        }
        return authHeader;
    }
}
