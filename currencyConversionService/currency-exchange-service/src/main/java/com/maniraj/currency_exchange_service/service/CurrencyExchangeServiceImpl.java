package com.maniraj.currency_exchange_service.service;

import com.maniraj.currency_exchange_service.bean.CurrencyExchange;
import com.maniraj.currency_exchange_service.repository.CurrencyExchangeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    CurrencyExchangeRepository currencyExchangeRepository;

    CurrencyExchangeServiceImpl(CurrencyExchangeRepository currencyExchangeRepository) {
        this.currencyExchangeRepository = currencyExchangeRepository;
    }
    @Override
    public List<CurrencyExchange> getAllCurrencyExchanges() {
        return currencyExchangeRepository.findAll();
    }

    public CurrencyExchange getByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency) {
        return currencyExchangeRepository.findByFromCurrencyAndToCurrency(fromCurrency, toCurrency);
    }
}
