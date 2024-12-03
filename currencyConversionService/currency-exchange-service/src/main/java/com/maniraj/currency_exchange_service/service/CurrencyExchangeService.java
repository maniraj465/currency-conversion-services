package com.maniraj.currency_exchange_service.service;

import com.maniraj.currency_exchange_service.bean.CurrencyExchange;

import java.util.List;

public interface CurrencyExchangeService {
    public List<CurrencyExchange> getAllCurrencyExchanges();

    public CurrencyExchange getByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency);

}
