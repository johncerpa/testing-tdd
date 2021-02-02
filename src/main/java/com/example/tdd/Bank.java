package com.example.tdd;

public class Bank {
    Money reduce(Expression source, String toCurrency) {
        return source.reduce(toCurrency);
    }
}
