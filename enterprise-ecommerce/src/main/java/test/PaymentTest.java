package test;

import com.ecommerce.strategy.CreditCardPaymentStrategy;
import com.ecommerce.strategy.PaymentContext;
import com.ecommerce.strategy.PayPalPaymentStrategy;

import java.math.BigDecimal;

public class PaymentTest {

    public static void main(String[] args) {

        PaymentContext paymentContext =
                new PaymentContext(new CreditCardPaymentStrategy());

        boolean creditCardResult =
                paymentContext.executePayment(
                        new BigDecimal("86200.00")
                );

        System.out.println(
                "Credit Card Payment Successful: " + creditCardResult
        );

        paymentContext.setPaymentStrategy(
                new PayPalPaymentStrategy()
        );

        boolean paypalResult =
                paymentContext.executePayment(
                        new BigDecimal("1449.00")
                );

        System.out.println(
                "PayPal Payment Successful: " + paypalResult
        );
    }
}