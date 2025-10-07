package com.maniraj.api_gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(ApiGatewayConfiguration.class);

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {

        logger.info("ApiGatewayConfiguration:::gatewayRouter:::Begin");

//        return builder.routes()
//                .route("currency-exchange-route", p -> p.path("/currency-exchange/**")
//                        .filters(f -> f
//                                .filter((exchange, chain) -> {
//                                    logger.info("Routing to currency-exchange-service: {}", exchange.getRequest().getPath());
//                                    return chain.filter(exchange);
//                                })
////                                .rewritePath("/currency-exchange/(?<segment>.*)", "/${segment}")
//                        )
//                        .uri("lb://currency-exchange-service"))
////                      .uri("http://localhost:8000"))
//                .route("currency-conversion-route", p -> p.path("/currency-conversion/**")
//                        .filters(f -> f
//                                .filter((exchange, chain) -> {
//                                    logger.info("Routing to currency-conversion-service: {}", exchange.getRequest().getPath());
//                                    return chain.filter(exchange);
//                                })
//                                .rewritePath("/currency-conversion/(?<segment>.*)", "/${segment}")
//                        )
//                        .uri("lb://currency-conversion-service"))
//                .route("get-route", p -> p.path("/get")
//                        .filters(f -> f
//                                .addRequestHeader("X-h-name", "X-h-value")
//                                .addRequestParameter("R-params", "R-values"))
//                        .uri("http://httpbin.org:80"))
//                .build();




        Function<PredicateSpec, Buildable<Route>> getRouteFunction =
                p -> p.path("/get")
                        .filters(f -> f
                                .addRequestHeader("X-h-name", "X-h-value")
                                .addRequestParameter("R-params", "R-values"))
                        .uri("http://httpbin.org:80");

        Function<PredicateSpec, Buildable<Route>> currencyExchangeRouteFunction =
                p -> p.path("/currency-exchange/**")
                        .uri("lb://currency-exchange-service");

        Function<PredicateSpec, Buildable<Route>> currencyConversionRouteFunction =
                p -> p.path("/currency-conversion/**")
                        .uri("lb://currency-conversion-service");

        Function<PredicateSpec, Buildable<Route>> currencyConversionFeignRouteFunction =
                p -> p.path("/currency-conversion-feign/**")
                        .uri("lb://currency-conversion-service");

        Function<PredicateSpec, Buildable<Route>> currencyConversionNewRouteFunction =
                p -> p.path("/currency-conversion-new/**")
                        .filters(f -> f
                                .rewritePath(
                                        "/currency-conversion-new/(?<segment>.*)",
                                        "/currency-conversion-feign/${segment}"))
                        .uri("lb://currency-conversion-service");

        logger.info("ApiGatewayConfiguration:::gatewayRouter:::End");


        logger.info("ApiGatewayConfiguration:::gatewayRouter:::End");

        return builder.routes()
                .route(getRouteFunction)
                .route(currencyExchangeRouteFunction)
                .route(currencyConversionRouteFunction)
                .route(currencyConversionFeignRouteFunction)
                .route(currencyConversionNewRouteFunction)
                .build();
    }
}
