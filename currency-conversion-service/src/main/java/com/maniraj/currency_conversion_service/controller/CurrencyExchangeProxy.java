package com.maniraj.currency_conversion_service.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.maniraj.currency_conversion_service.bean.CurrencyConversion;

//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
//@FeignClient(name = "currency-exchange")
@FeignClient(name = "currency-exchange-service")
public interface CurrencyExchangeProxy {

    @GetMapping("currency-exchange/from/{fromCurrency}/to/{toCurrency}")
    public CurrencyConversion retriveExchangeValue(@PathVariable String fromCurrency, @PathVariable String toCurrency);
}
