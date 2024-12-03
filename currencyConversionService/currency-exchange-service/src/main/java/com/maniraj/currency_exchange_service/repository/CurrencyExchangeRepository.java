package com.maniraj.currency_exchange_service.repository;

import com.maniraj.currency_exchange_service.bean.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

    CurrencyExchange findByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency);
}
