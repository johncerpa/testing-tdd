package com.example.tdd;

public class Sum implements Expression {
    Money augmend;
    Money addend;

    public Sum(Money augmend, Money addend) {
        this.augmend = augmend;
        this.addend = addend;
    }

    public Money reduce(String to) {
        return new Money(augmend.amount + addend.amount, to);
    }
}
