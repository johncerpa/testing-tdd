package com.example.tdd;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MoneyTest {

    @Test
    void testMultiplicationDollar() {
        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));

        Money fiveF = Money.franc(5);
        assertEquals(Money.franc(10), fiveF.times(2));
        assertEquals(Money.franc(15), fiveF.times(3));
    }

    @Test
    void testEqualityDollar() {
        assertEquals(Money.dollar(5), Money.dollar(5));
        assertNotEquals(Money.dollar(5), Money.dollar(8));

        assertEquals(Money.franc(5), Money.franc(5));
        assertNotEquals(Money.franc(5), Money.dollar(5));
    }

    @Test
    void testCurrency() {
        assertEquals("USD", Money.dollar(1).currency());
        assertEquals("CHF", Money.franc(1).currency());
    }

    @Test
    void testSimpleAddition() {
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Money reduced = new Bank().reduce(sum, "USD");

        assertEquals(Money.dollar(10), reduced);
    }

    @Test
    void testPlusReturnsSum() {
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertEquals(five, sum.augmend);
        assertEquals(five, sum.addend);
    }

    @Test
    void testReduceSum() {
        Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
        Money result = new Bank().reduce(sum, "USD");
        assertEquals(Money.dollar(7), result);
    }

    @Test
    void testReduceMoneyDifferentCurrency() {
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);

        Money result = bank.reduce(Money.franc(2), "USD");
        assertEquals(Money.dollar(1), result);
    }

    @Test
    void testIdentityRate() {
        Bank bank = new Bank();
        assertEquals(1, bank.rate("USD", "USD"));
        assertEquals(1, bank.rate("CHF", "CHF"));
    }

    @Test
    void testMixedAddition() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);

        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);

        Money result = bank.reduce(fiveBucks.plus(tenFrancs), "USD");
        assertEquals(Money.dollar(10), result);
    }

    @Test
    void testSumTimes() {
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);

        Expression sum = new Sum(Money.dollar(5), Money.franc(10)).times(2);
        Money result = bank.reduce(sum, "USD");

        assertEquals(Money.dollar(20), result);
    }
}
