package org.example.boxes_task.util;

import org.example.boxes_task.entity.Currency;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class CurrencyConverter {

    private static final Map<Currency, Map<Currency, BigDecimal>> RATES = Map.of(
            Currency.EUR, Map.of(Currency.USD, BigDecimal.valueOf(1.1), Currency.GBP, BigDecimal.valueOf(0.85), Currency.EUR, BigDecimal.ONE),
            Currency.USD, Map.of(Currency.EUR, BigDecimal.valueOf(0.91), Currency.GBP, BigDecimal.valueOf(0.77), Currency.USD, BigDecimal.ONE),
            Currency.GBP, Map.of(Currency.EUR, BigDecimal.valueOf(1.17), Currency.USD, BigDecimal.valueOf(1.3), Currency.GBP, BigDecimal.ONE)
    );

    public static BigDecimal convert(Currency from, Currency to, BigDecimal amount) {
        if (from == to) return amount;
        BigDecimal rate = RATES.getOrDefault(from, Map.of()).get(to);
        if (rate == null) throw new RuntimeException("Exchange rate not found");
        return amount.multiply(rate).setScale(2, RoundingMode.HALF_UP);
    }
}
