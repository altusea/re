package org.example.playground.stream;

import java.math.BigDecimal;
import java.util.Currency;

public record Money(BigDecimal amount, Currency currency) {

    public Money add(Money money) {
        return new Money(money.amount.add(this.amount), currency);
    }

    public Money multiply(BigDecimal multiplier) {
        return new Money(amount.multiply(multiplier), currency);
    }

    public static void main(String[] args) {
        Currency currency = Currency.getInstance("CNY");
        System.out.println(currency);
        System.out.println(Currency.getAvailableCurrencies());
    }
}
