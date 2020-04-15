package com.example.convert_money;

public class Money {
    String symbol;
    double value;

    public Money(String symbol, double value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getRate(Money money) {
        return (double) Math.round(this.value / money.value * 1000000)/1000000;
    }
}
