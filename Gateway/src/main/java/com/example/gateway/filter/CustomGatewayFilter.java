package com.example.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class CustomGatewayFilter implements GatewayFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
       return chain.filter(exchange)
               .then(Mono.fromRunnable(() -> {
                   System.out.println(exchange.getRequest().getURI().getPath());
                   System.out.println("CustomGatewayFilter : traitement apres le filter");
               }));
    }


}
