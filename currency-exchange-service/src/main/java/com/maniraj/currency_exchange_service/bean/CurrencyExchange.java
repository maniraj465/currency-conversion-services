package com.maniraj.currency_exchange_service.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "currency_exchange")
public class CurrencyExchange {
    @Id
    private Long id;

    @Column(name = "currency_from")
    private String fromCurrency;

    @Column(name = "currency_to")
    private String toCurrency;
    private BigDecimal conversionMultiple;
    private String environment;
}
