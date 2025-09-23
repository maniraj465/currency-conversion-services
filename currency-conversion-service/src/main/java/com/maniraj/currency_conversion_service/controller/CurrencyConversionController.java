package com.maniraj.currency_conversion_service.controller;

import com.maniraj.currency_conversion_service.bean.CurrencyConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    private CurrencyExchangeProxy currencyExchangeProxy;

    CurrencyConversionController(CurrencyExchangeProxy currencyExchangeProxy) {
        this.currencyExchangeProxy = currencyExchangeProxy;
    }

    @GetMapping("currency-conversion/from/{fromCurrency}/to/{toCurrency}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String fromCurrency,
                                                          @PathVariable String toCurrency,
                                                          @PathVariable BigDecimal quantity) {

        System.out.println("currency-conversion/from/{fromCurrency}/to/{toCurrency}/quantity/{quantity}");
        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("fromCurrency", fromCurrency);
        uriVariables.put("toCurrency", toCurrency);

        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate()
                .getForEntity("http://localhost:8000/currency-exchange/from/{fromCurrency}/to/{toCurrency}",
                        CurrencyConversion.class, uriVariables);
        CurrencyConversion currencyConversion = responseEntity.getBody();

        return new CurrencyConversion(currencyConversion.getId(),
                fromCurrency, toCurrency, quantity, currencyConversion.getConversionMultiple(),
                currencyConversion.getConversionMultiple().multiply(quantity), currencyConversion.getEnvironment());
    }

    @GetMapping("currency-conversion-feign/from/{fromCurrency}/to/{toCurrency}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String fromCurrency,
                                                          @PathVariable String toCurrency,
                                                          @PathVariable BigDecimal quantity) {
        System.out.println("calculateCurrencyConversionFeign");
        CurrencyConversion currencyConversion = currencyExchangeProxy.retriveExchangeValue(fromCurrency, toCurrency);
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(currencyConversion.getConversionMultiple().multiply(quantity));

        return currencyConversion;
    }

    @GetMapping("currency-conversion-new/from/{fromCurrency}/to/{toCurrency}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionNew(@PathVariable String fromCurrency,
                                                               @PathVariable String toCurrency,
                                                               @PathVariable BigDecimal quantity) {
        System.out.println("calculateCurrencyConversionNew");
        CurrencyConversion currencyConversion = currencyExchangeProxy.retriveExchangeValue(fromCurrency, toCurrency);
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(currencyConversion.getConversionMultiple().multiply(quantity));

        return currencyConversion;
    }
    
}
