package com.example.tdd;

public abstract class Money {
    protected int amount;
    protected String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money dollar(int amount) {
        return new Dollar(amount, "USD");
    }

    public static Money franc(int amount) {
        return new Franc(amount, "CHF");
    }

    protected String currency() {
        return currency;
    }

    abstract Money times(int multiplier);

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !obj.getClass().equals(this.getClass())) return false;

        Money otherMoney = (Money) obj;
        return amount == otherMoney.amount;
    }
}
