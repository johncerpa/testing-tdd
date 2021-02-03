package com.example.tdd;

public class Sum implements Expression {
    final Expression augmend;
    final Expression addend;

    public Sum(Expression augmend, Expression addend) {
        this.augmend = augmend;
        this.addend = addend;
    }
    @Override
    public Money reduce(Bank bank, String to) {
        int amount = augmend.reduce(bank, to).amount + addend.reduce(bank, to).amount;
        return new Money(amount, to);
    }

    @Override
    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }

    @Override
    public Expression times(int multiplier) {
        return new Sum(augmend.times(multiplier), addend.times(multiplier));
    }
}
