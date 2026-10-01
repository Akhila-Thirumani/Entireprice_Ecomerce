package com.ecommerce.strategy;

import java.math.BigDecimal;

public class CreditCardPaymentStrategy implements PaymentStrategy {

    @Override
    public boolean pay(BigDecimal amount) {

        if (amount == null || amount.signum() <= 0) {
            return false;
        }

        System.out.println(
                "Payment made using Credit Card: " + amount
        );

        return true;
    }
}