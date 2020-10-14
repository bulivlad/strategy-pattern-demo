package io.dotinc.strategydemo.strategy;

import io.dotinc.strategydemo.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author vladclaudiubulimac on 14/10/2020.
 */

@Slf4j
@Component
public class CashOnDeliveryPaymentStrategy implements PaymentStrategy {

    @Override
    public void processPayment(Order order) {
        log.info("Processing cash on delivery payment for {}", order);
    }

}
