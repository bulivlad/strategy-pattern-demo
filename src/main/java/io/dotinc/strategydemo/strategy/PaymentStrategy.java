package io.dotinc.strategydemo.strategy;

import io.dotinc.strategydemo.model.Order;

/**
 * @author vladclaudiubulimac on 14/10/2020.
 */

public interface PaymentStrategy {
    void processPayment(Order order);
}
