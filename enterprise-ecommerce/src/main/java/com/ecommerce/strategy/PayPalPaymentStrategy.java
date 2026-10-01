package com.ecommerce.strategy;

import java.math.BigDecimal;

public class PayPalPaymentStrategy implements PaymentStrategy {

    @Override
    public boolean pay(BigDecimal amount) {

        if (amount == null || amount.signum() <= 0) {
            return false;
        }

        System.out.println(
                "Payment made using PayPal: " + amount
        );

        return true;
    }
}