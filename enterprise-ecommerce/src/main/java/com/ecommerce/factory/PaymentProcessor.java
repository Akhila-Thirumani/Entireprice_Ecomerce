package com.ecommerce.factory;

import java.math.BigDecimal;

public interface PaymentProcessor {

    boolean processPayment(BigDecimal amount);
}