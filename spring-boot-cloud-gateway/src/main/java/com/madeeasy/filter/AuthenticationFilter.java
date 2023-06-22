package com.madeeasy.filter;

import com.madeeasy.token.ValidateToken;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final RouteValidator validator;
    private final ValidateToken validateToken;

    public AuthenticationFilter(RouteValidator validator, ValidateToken validateToken) {
        super(Config.class);
        this.validator = validator;
        this.validateToken = validateToken;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            // check if the
            if (validator.isSecured.test(exchange.getRequest())) {
                // headers contain the token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }
                String authorizationHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
                if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                    authorizationHeader = authorizationHeader.substring(7);
                }
                try {
                    boolean flag = validateToken.validateToken(authorizationHeader);
                    System.out.println("flag = " + flag);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Invalid authorization");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {
        // Configuration properties for the filter (if any)
    }
}
