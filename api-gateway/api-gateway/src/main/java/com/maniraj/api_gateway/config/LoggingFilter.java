package com.maniraj.api_gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter {

    private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Path of the request is : {}", exchange.getRequest().getPath());
//        logger.debug("Path of the request is : {}", exchange.getRequest().getPath());
        logger.warn("Path of the request is : {}", exchange.getRequest().getPath());
//        logger.trace("Path of the request is : {}", exchange.getRequest().getPath());
        logger.error("Path of the request is : {}", exchange.getRequest().getPath());
        return chain.filter(exchange);
    }
}
