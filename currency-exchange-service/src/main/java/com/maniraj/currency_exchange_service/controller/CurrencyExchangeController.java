package com.maniraj.currency_exchange_service.controller;

import com.maniraj.currency_exchange_service.bean.CurrencyExchange;
import com.maniraj.currency_exchange_service.service.CurrencyExchangeService;
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

    CurrencyExchangeService currencyExchangeService;

    CurrencyExchangeController(CurrencyExchangeService currencyExchangeService) {
        this.currencyExchangeService = currencyExchangeService;
    }

    @Autowired
    private Environment environment;

    @GetMapping("currency-exchange")
    public List<CurrencyExchange> retriveAllExchangeValue() {
        String port = environment.getProperty("local.server.port");
        return currencyExchangeService.getAllCurrencyExchanges();
    }

    @GetMapping("currency-exchange/from/{fromCurrency}/to/{toCurrency}")
    public CurrencyExchange retriveExchangeValue(@PathVariable String fromCurrency, @PathVariable String toCurrency) {
        String port = environment.getProperty("local.server.port");
        CurrencyExchange currencyExchange = currencyExchangeService.getByFromCurrencyAndToCurrency(fromCurrency, toCurrency);
        currencyExchange.setEnvironment("PORT : " + port);
        System.out.println("local.server.port");
        return currencyExchange;
    }

    @GetMapping("currency-exchange-feign/from/{fromCurrency}/to/{toCurrency}")
    public CurrencyExchange retriveExchangeValueFeign(@PathVariable String fromCurrency, @PathVariable String toCurrency) {
        String port = environment.getProperty("local.server.port");
        CurrencyExchange currencyExchange = currencyExchangeService.getByFromCurrencyAndToCurrency(fromCurrency, toCurrency);
        currencyExchange.setEnvironment("PORT : " + port);

        System.out.println("local.server.port");

        return currencyExchange;
    }
}
