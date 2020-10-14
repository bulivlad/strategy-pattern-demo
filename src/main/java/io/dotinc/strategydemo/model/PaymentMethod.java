package io.dotinc.strategydemo.model;

/**
 * @author vladclaudiubulimac on 14/10/2020.
 */
public enum PaymentMethod {

    CREDIT_CARD("creditCard"),
    CASH_ON_DELIVERY("cashOnDelivery");

    String value;

    PaymentMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
