package com.ecommerce.factory;

import java.math.BigDecimal;

public class CreditCardPaymentProcessor implements PaymentProcessor {

    @Override
    public boolean processPayment(BigDecimal amount) {

        if (amount == null || amount.signum() <= 0) {
            return false;
        }

        System.out.println(
                "Processing credit card payment of: " + amount
        );

        return true;
    }
}