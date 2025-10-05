package com.maniraj.currency_conversion_service.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyConversion {
    public Long id;
    public String fromCurrency;
    public String toCurrency;
    public BigDecimal quantity;
    public BigDecimal conversionMultiple;
    public BigDecimal totalCalculatedAmount;
}
