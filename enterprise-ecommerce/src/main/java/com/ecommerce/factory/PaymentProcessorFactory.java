package com.ecommerce.factory;

public class PaymentProcessorFactory {

    private PaymentProcessorFactory() {
    }

    public static PaymentProcessor createProcessor(String paymentMethod) {

        if (paymentMethod == null || paymentMethod.isBlank()) {
            throw new IllegalArgumentException(
                    "Payment method is required"
            );
        }

        switch (paymentMethod.trim().toUpperCase()) {

            case "CREDIT_CARD":
                return new CreditCardPaymentProcessor();

            case "PAYPAL":
                return new PayPalPaymentProcessor();

            default:
                throw new IllegalArgumentException(
                        "Unsupported payment method: " + paymentMethod
                );
        }
    }
}