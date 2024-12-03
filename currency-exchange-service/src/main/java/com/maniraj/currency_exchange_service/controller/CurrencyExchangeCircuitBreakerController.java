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

    static int rertyCount = 1;

    @Autowired
    private Environment environment;
    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeCircuitBreakerController.class);
    private CurrencyExchangeService currencyExchangeService;

    CurrencyExchangeCircuitBreakerController(CurrencyExchangeService currencyExchangeService) {
        this.currencyExchangeService = currencyExchangeService;
    }

    @GetMapping("currency-exchange")
    @Retry(name = "currency-exchange-get-all")
    public List<CurrencyExchange> retriveAllExchangeValue() {
        String port = environment.getProperty("local.server.port");
        logger.info("CurrencyExchangeCircuitBreakerController:::retriveAllExchangeValue:::rertyCount : " + rertyCount);
        return currencyExchangeService.getAllCurrencyExchanges();
    }

    @GetMapping("currency-exchange/from/{fromCurrency}/to/{toCurrency}")
    public CurrencyExchange retriveExchangeValue(@PathVariable String fromCurrency, @PathVariable String toCurrency) {
        String port = environment.getProperty("local.server.port");
//        return currencyExchangeService.getAllCurrencyExchanges().stream().filter(ex -> {
//            return ex.getFromCurrency().equals(fromCurrency) && ex.getToCurrency().equals(toCurrency);
//        }).collect(Collectors.toList());
        CurrencyExchange currencyExchange = currencyExchangeService.getByFromCurrencyAndToCurrency(fromCurrency, toCurrency);
        currencyExchange.setEnvironment("PORT : " + port);
        return currencyExchange;
    }
}
