package com.maniraj.currency_exchange_service.controller;

import com.maniraj.currency_exchange_service.bean.CurrencyExchange;
import com.maniraj.currency_exchange_service.service.CurrencyExchangeService;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "cb")
public class CurrencyExchangeCircuitBreakerController {

    private final Logger logger = LoggerFactory.getLogger(CurrencyExchangeCircuitBreakerController.class);
    private static int retryCount = 1;
    @Autowired
    private Environment environment;
    CurrencyExchangeService currencyExchangeService;

    CurrencyExchangeCircuitBreakerController(CurrencyExchangeService currencyExchangeService) {
        this.currencyExchangeService = currencyExchangeService;
    }

    @GetMapping("currency-exchange")
    @Retry(name = "currency-exchange-get-all")
    public List<CurrencyExchange> retrieveAllExchangeValues() {

        logger.info("CurrencyExchangeCircuitBreakerController:::retrieveAllExchangeValues:::Begin");

        String port = environment.getProperty("local.server.port");
        logger.info("CurrencyExchangeCircuitBreakerController:::retrieveAllExchangeValues:::retryCount : " + retryCount);

        logger.info("CurrencyExchangeCircuitBreakerController:::retrieveAllExchangeValues:::End");
        return currencyExchangeService.getAllCurrencyExchanges();
    }

    @GetMapping("currency-exchange/from/{fromCurrency}/to/{toCurrency}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String fromCurrency, @PathVariable String toCurrency) {

        logger.info("CurrencyExchangeCircuitBreakerController:::retrieveExchangeValue:::Begin");

        String port = environment.getProperty("local.server.port");

//        return currencyExchangeService.getAllCurrencyExchanges().stream().filter(ex -> {
//            return ex.getFromCurrency().equals(fromCurrency) && ex.getToCurrency().equals(toCurrency);
//        }).collect(Collectors.toList());

        CurrencyExchange currencyExchange = currencyExchangeService.getByFromCurrencyAndToCurrency(fromCurrency, toCurrency);

        logger.info("CurrencyExchangeCircuitBreakerController:::retrieveExchangeValue:::End");
        return currencyExchange;
    }
}
