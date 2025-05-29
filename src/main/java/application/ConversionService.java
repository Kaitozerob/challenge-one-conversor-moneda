package main.java.application;

import main.java.domain.CurrencyConverter;

public class ConversionService {
    private CurrencyConverter converter;

    public ConversionService() {
        this.converter = new CurrencyConverter();
    }

    public double convert(double amount, double exchangeRate) {
        // Aquí puedes agregar validaciones o lógica adicional si es necesario
        return converter.convert(amount, exchangeRate);
    }
}
