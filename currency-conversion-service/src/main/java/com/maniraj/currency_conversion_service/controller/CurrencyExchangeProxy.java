package com.maniraj.currency_conversion_service.controller;

import com.maniraj.currency_conversion_service.bean.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

//@FeignClient(name = "currency-exchange", url = "localhost:8000")
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {

    @GetMapping("currency-exchange/from/{fromCurrency}/to/{toCurrency}")
    public CurrencyConversion retriveExchangeValue(@PathVariable String fromCurrency, @PathVariable String toCurrency);
}
