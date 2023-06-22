package com.madeeasy.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {
    public static final List<String> OPEN_ENDPOINTS =
            List.of(
                    "/api/authentication",
                    "/api/register"
            );

    public Predicate<ServerHttpRequest> isSecured =
            serverHttpRequest -> OPEN_ENDPOINTS
                    .stream()
                    .noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));
}
