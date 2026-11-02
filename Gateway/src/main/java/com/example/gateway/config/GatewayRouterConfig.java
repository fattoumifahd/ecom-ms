package com.example.gateway.config;

import com.example.gateway.filter.CustomGatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
public class GatewayRouterConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("productms",r -> r
                        .path("/productms/**")
                        .filters(f -> f
                                .rewritePath("/productms/(?<ramaining>.*)","${remainign}")
                                .addRequestHeader("X-Request-Origin", "Gateway")
                                .filter(new CustomGatewayFilter())

                        )
                        .uri("lb://productms")
                )
                .route("identityms", r -> r
                        .path("/identityms/**")
                        .filters(f -> f
                                .rewritePath("/identityms/(?<remaining>.*)","${remaining}")
                                .addRequestHeader("X-Request-Origin", "Gateway")
                                .filter(new CustomGatewayFilter())
                        )
                        .uri("lb://identityms")
                )
                .build();


    }
}
