package main.java.domain;

public class CurrencyConverter {
    public double convert(double amount, double exchangeRate){
        return amount * exchangeRate;
    }
}
