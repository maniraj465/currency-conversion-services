package com.maniraj.currency_exchange_service.controller;

import com.maniraj.currency_exchange_service.bean.CurrencyExchange;
import com.maniraj.currency_exchange_service.service.CurrencyExchangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CurrencyExchangeController {

    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
    CurrencyExchangeService currencyExchangeService;

    CurrencyExchangeController(CurrencyExchangeService currencyExchangeService) {
        this.currencyExchangeService = currencyExchangeService;
    }

    @Autowired
    private Environment environment;

    @GetMapping("currency-exchange")
    public List<CurrencyExchange> retriveAllExchangeValue() {
        logger.info("CurrencyExchangeController:::retriveAllExchangeValue:::Begin");

        String port = environment.getProperty("server.port");
        List<CurrencyExchange> currencyExchanges = currencyExchangeService.getAllCurrencyExchanges();

        System.out.println("server.port - " + port);

        logger.info("CurrencyExchangeController:::retriveAllExchangeValue:::End");
        return currencyExchanges;
    }

    @GetMapping("currency-exchange/from/{fromCurrency}/to/{toCurrency}")
    public CurrencyExchange retriveExchangeValue(@PathVariable String fromCurrency, @PathVariable String toCurrency) {
        logger.info("CurrencyExchangeController:::retriveExchangeValue:::Begin");

        String port = environment.getProperty("server.port");
        CurrencyExchange currencyExchange = currencyExchangeService.getByFromCurrencyAndToCurrency(fromCurrency, toCurrency);
        currencyExchange.setEnvironment("PORT : " + port);

        System.out.println("server.port - " + port);

        logger.info("CurrencyExchangeController:::retriveExchangeValue:::End");
        return currencyExchange;
    }

    @GetMapping("currency-exchange-feign/from/{fromCurrency}/to/{toCurrency}")
    public CurrencyExchange retriveExchangeValueFeign(@PathVariable String fromCurrency, @PathVariable String toCurrency) {
        logger.info("CurrencyExchangeController:::retriveExchangeValueFeign:::Begin");

        String port = environment.getProperty("server.port");

        CurrencyExchange currencyExchange = currencyExchangeService.getByFromCurrencyAndToCurrency(fromCurrency, toCurrency);
        currencyExchange.setEnvironment("PORT : " + port);

        logger.info("server.port:::" + port);

        logger.info("CurrencyExchangeController:::retriveExchangeValueFeign:::End");
        return currencyExchange;
    }
}


//2025-09-23T22:47:48.546+05:30  INFO 16424 --- [currency-exchange] [nio-8000-exec-1] [a7e41211a497e4361e1feb440abd8e5c-d443f1e5d369ff70] c.m.c.c.CurrencyExchangeController       : CurrencyExchangeController:::retriveExchangeValue:::End