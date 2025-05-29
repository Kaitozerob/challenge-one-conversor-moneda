package main.java.application;

import main.java.domain.CurrencyConverter;

public class ConversionService {
    private CurrencyConverter converter;

    public ConversionService() {
        this.converter = new CurrencyConverter();
    }

    public double convert(double amount, double exchangeRate) {
        return converter.convert(amount, exchangeRate);
    }
}
