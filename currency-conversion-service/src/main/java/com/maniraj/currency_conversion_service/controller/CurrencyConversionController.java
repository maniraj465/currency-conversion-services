package com.maniraj.currency_conversion_service.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.maniraj.currency_conversion_service.bean.CurrencyConversion;

@RestController
public class CurrencyConversionController {

    private final Logger logger = LoggerFactory.getLogger(CurrencyConversionController.class);
    @Autowired
    private RestTemplate restTemplate;
    CurrencyExchangeProxy currencyExchangeProxy;

    CurrencyConversionController(CurrencyExchangeProxy currencyExchangeProxy) {
        this.currencyExchangeProxy = currencyExchangeProxy;
    }

    @GetMapping("currency-conversion/from/{fromCurrency}/to/{toCurrency}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String fromCurrency,
                                                          @PathVariable String toCurrency,
                                                          @PathVariable BigDecimal quantity) {

        logger.info("CurrencyConversionController:::calculateCurrencyConversion:::Begin");

        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("fromCurrency", fromCurrency);
        uriVariables.put("toCurrency", toCurrency);

        ResponseEntity<CurrencyConversion> responseEntity = restTemplate
                .getForEntity("http://localhost:8000/currency-exchange/from/{fromCurrency}/to/{toCurrency}",
                        CurrencyConversion.class, uriVariables);

        CurrencyConversion currencyConversion = responseEntity.getBody();
        assert currencyConversion != null;
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));
        logger.info("CurrencyConversionController:::calculateCurrencyConversion:::End");

        return currencyConversion;
    }

    @GetMapping("currency-conversion-feign/from/{fromCurrency}/to/{toCurrency}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String fromCurrency,
                                                          @PathVariable String toCurrency,
                                                          @PathVariable BigDecimal quantity) {

        logger.info("CurrencyConversionController:::calculateCurrencyConversionFeign:::Begin");

        CurrencyConversion currencyConversion = currencyExchangeProxy.retriveExchangeValue(fromCurrency, toCurrency);
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(currencyConversion.getConversionMultiple().multiply(quantity));

        logger.info("CurrencyConversionController:::calculateCurrencyConversionFeign:::End");
        return currencyConversion;
    }

    @GetMapping("currency-conversion-new/from/{fromCurrency}/to/{toCurrency}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionNew(@PathVariable String fromCurrency,
                                                               @PathVariable String toCurrency,
                                                               @PathVariable BigDecimal quantity) {

        logger.info("CurrencyConversionController:::calculateCurrencyConversionNew:::Begin");

        CurrencyConversion currencyConversion = currencyExchangeProxy.retriveExchangeValue(fromCurrency, toCurrency);
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(currencyConversion.getConversionMultiple().multiply(quantity));

        logger.info("CurrencyConversionController:::calculateCurrencyConversionNew:::End");
        return currencyConversion;
    }
    
}
