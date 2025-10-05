package com.maniraj.currency_conversion_service.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.maniraj.currency_conversion_service.bean.CurrencyConversion;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("cb")
public class CurrencyConversionCircuitBreakerController {

    private final Logger logger = LoggerFactory.getLogger(CurrencyConversionCircuitBreakerController.class);
    @Autowired
    private RestTemplate restTemplate;
    CurrencyExchangeProxy currencyExchangeProxy;

    
    CurrencyConversionCircuitBreakerController(CurrencyExchangeProxy currencyExchangeProxy) {
        this.currencyExchangeProxy = currencyExchangeProxy;
    }


//    @CircuitBreaker(name = "currency-conversion")
//    @Retry(name="default", fallbackMethod = "failureResponse")
//    @CircuitBreaker(name="currency-conversion-api", fallbackMethod = "failureResponse")
//    @CircuitBreaker(name="default", fallbackMethod = "failureResponse")
    @Retry(name="currency-conversion-api", fallbackMethod = "failureResponse")
    @GetMapping("currency-conversion/from/{fromCurrency}/to/{toCurrency}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String fromCurrency,
                                                          @PathVariable String toCurrency,
                                                          @PathVariable BigDecimal quantity) {

        logger.info("CurrencyConversionCircuitBreakerController:::calculateCurrencyConversion:::Begin");

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
        logger.info("CurrencyConversionCircuitBreakerController:::calculateCurrencyConversion:::End");
        return currencyConversion;
    }

    /**
     * This method is fallbackMethod for Retry & CircuitBreaker of calculateCurrencyConversion API
     * @return CurrencyConversion object with environment status input received
     */
    @SuppressWarnings("unused")
	private CurrencyConversion failureResponse(String fromCurrency,
                                               String toCurrency,
                                               BigDecimal quantity,
                                               Exception ex) {
        logger.info("CurrencyConversionCircuitBreakerController:::failureResponse:::Begin");

        logger.error("CurrencyConversionCircuitBreakerController:::failureResponse:::{}", ex.toString());

        logger.info("CurrencyConversionCircuitBreakerController:::failureResponse:::End");
        return new CurrencyConversion(
                0L,
                fromCurrency,
                toCurrency,
                quantity,
                new BigDecimal(0),
                new BigDecimal(0)
        );

    }
}
