package com.maniraj.currency_exchange_service.service;

import com.maniraj.currency_exchange_service.bean.CurrencyExchange;
import com.maniraj.currency_exchange_service.controller.CurrencyExchangeController;
import com.maniraj.currency_exchange_service.repository.CurrencyExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    private final Logger logger = LoggerFactory.getLogger(CurrencyExchangeServiceImpl.class);

    CurrencyExchangeRepository currencyExchangeRepository;

    CurrencyExchangeServiceImpl(CurrencyExchangeRepository currencyExchangeRepository) {
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    @Override
    public List<CurrencyExchange> getAllCurrencyExchanges() {
        logger.info("CurrencyExchangeServiceImpl:::getAllCurrencyExchanges:::Begin");
        logger.info("CurrencyExchangeServiceImpl:::getAllCurrencyExchanges:::End");
        return currencyExchangeRepository.findAll();
    }

    public CurrencyExchange getByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency) {
        logger.info("CurrencyExchangeServiceImpl:::getByFromCurrencyAndToCurrency:::Begin");
        logger.info("CurrencyExchangeServiceImpl:::getByFromCurrencyAndToCurrency:::End");
        return currencyExchangeRepository.findByFromCurrencyAndToCurrency(fromCurrency, toCurrency);
    }
}
