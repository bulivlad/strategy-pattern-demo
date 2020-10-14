package io.dotinc.strategydemo;

import io.dotinc.strategydemo.model.Item;
import io.dotinc.strategydemo.model.Order;
import io.dotinc.strategydemo.model.PaymentMethod;
import io.dotinc.strategydemo.strategy.CashOnDeliveryPaymentStrategy;
import io.dotinc.strategydemo.strategy.CreditCardPaymentStrategy;
import io.dotinc.strategydemo.strategy.PaymentStrategy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class StrategydemoApplication {

	private Map<String, PaymentStrategy> paymentStrategies;

	public StrategydemoApplication(CreditCardPaymentStrategy creditCardPaymentStrategy,
								   CashOnDeliveryPaymentStrategy cashOnDeliveryPaymentStrategy) {
		paymentStrategies = new HashMap<>();
		paymentStrategies.put("creditCard", creditCardPaymentStrategy);
		paymentStrategies.put("cashOnDelivery", cashOnDeliveryPaymentStrategy);
	}

	public static void main(String[] args) {
		SpringApplication.run(StrategydemoApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner() {
		return command -> {
			Order creditCardOrder = new Order();
			creditCardOrder.setDescription("Use credit card as payment method");
			creditCardOrder.addLineItem(new Item("Clean Code: A Handbook of Agile Software Craftsmanship", 5.0));
			creditCardOrder.addLineItem(new Item("Design Patterns in Java ", 15.00));
			creditCardOrder.addLineItem(new Item("Some expensive device I will not pay in cash", 1400.00));
			creditCardOrder.setPaymentMethod(PaymentMethod.CREDIT_CARD);

			determinePaymentStrategy(creditCardOrder).processPayment(creditCardOrder);

			Order cashOnDeliveryOrder = new Order();
			cashOnDeliveryOrder.setDescription("Use cash on delivery as payment method");
			cashOnDeliveryOrder.addLineItem(new Item("Clean Code: A Handbook of Agile Software Craftsmanship", 5.0));
			cashOnDeliveryOrder.addLineItem(new Item("Design Patterns in Java ", 15.00));
			cashOnDeliveryOrder.setPaymentMethod(PaymentMethod.CASH_ON_DELIVERY);

			determinePaymentStrategy(cashOnDeliveryOrder).processPayment(cashOnDeliveryOrder);
		};
	}

	private PaymentStrategy determinePaymentStrategy(Order order) {
		return paymentStrategies.entrySet()
						.stream()
						.filter(entry -> entry.getKey().equals(order.getPaymentMethod().getValue()))
						.findFirst()
						.map(Map.Entry::getValue)
						.orElseThrow(() -> new IllegalArgumentException("Unknown payment method" + order.getPaymentMethod().getValue()));
	}

}
