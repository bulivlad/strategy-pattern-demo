package io.dotinc.strategydemo.util;

import io.dotinc.strategydemo.model.Order;
import io.dotinc.strategydemo.model.PaymentMethod;
import io.dotinc.strategydemo.strategy.CashOnDeliveryPaymentStrategy;
import io.dotinc.strategydemo.strategy.CreditCardPaymentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author vladclaudiubulimac on 14/10/2020.
 */

@Component
public class PaymentHelper {
    @Autowired CashOnDeliveryPaymentStrategy cashOnDeliveryPaymentStrategy;
    @Autowired CreditCardPaymentStrategy creditCardPaymentStrategy;

    public void processPayment(Order order) {
        if(PaymentMethod.CASH_ON_DELIVERY.equals(order.getPaymentMethod())) {
            cashOnDeliveryPaymentStrategy.processPayment(order);
        } else if(PaymentMethod.CREDIT_CARD.equals(order.getPaymentMethod())) {
            creditCardPaymentStrategy.processPayment(order);
        }
    }
}
