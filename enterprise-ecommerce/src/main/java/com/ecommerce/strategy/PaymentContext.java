package com.ecommerce.strategy;

import java.math.BigDecimal;

public class PaymentContext {

    private PaymentStrategy paymentStrategy;

    public PaymentContext(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public boolean executePayment(BigDecimal amount) {

        if (paymentStrategy == null) {
            throw new IllegalStateException(
                    "Payment strategy has not been configured"
            );
        }

        return paymentStrategy.pay(amount);
    }
}