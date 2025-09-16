package com.maniraj.api_gateway.config;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        Function<PredicateSpec, Buildable<Route>> getRouteFunction =
                p -> p.path("/get")
                        .filters(f -> f
                                .addRequestHeader("X-h-name", "X-h-value")
                                .addRequestParameter("R-params", "R-values"))
                        .uri("http://httpbin.org:80");

        Function<PredicateSpec, Buildable<Route>> currencyExchangeRouteFunction =
                p -> p.path("/currency-exchange/**")
                        .uri("lb://currency-exchange");

        Function<PredicateSpec, Buildable<Route>> currencyConversionRouteFunction =
                p -> p.path("/currency-conversion/**")
                        .uri("lb://currency-conversion");

        Function<PredicateSpec, Buildable<Route>> currencyConversionFeignRouteFunction =
                p -> p.path("/currency-conversion-feign/**")
                        .uri("lb://currency-conversion");

        Function<PredicateSpec, Buildable<Route>> currencyConversionNewRouteFunction =
                p -> p.path("/currency-conversion-new/**")
                        .filters(f -> f
                                .rewritePath(
                                        "/currency-conversion-new/(?<segment>.*)",
                                        "/currency-conversion-feign/${segment}"))
                        .uri("lb://currency-conversion");

        return builder.routes()
                .route(getRouteFunction)
                .route(currencyExchangeRouteFunction)
                .route(currencyConversionRouteFunction)
                .route(currencyConversionFeignRouteFunction)
                .route(currencyConversionNewRouteFunction)
                .build();
    }
}
