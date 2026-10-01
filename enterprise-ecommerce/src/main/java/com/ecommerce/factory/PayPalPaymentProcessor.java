package com.ecommerce.factory;

import java.math.BigDecimal;

public class PayPalPaymentProcessor implements PaymentProcessor {

    @Override
    public boolean processPayment(BigDecimal amount) {

        if (amount == null || amount.signum() <= 0) {
            return false;
        }

        System.out.println(
                "Processing PayPal payment of: " + amount
        );

        return true;
    }
}