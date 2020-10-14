package io.dotinc.strategydemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vladclaudiubulimac on 14/10/2020.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    public List<Item> items = new ArrayList<>();

    public String description = "";

    private Double total = 0.0;

    private PaymentMethod paymentMethod;

    private String creditCardNumber;

    private final LocalDateTime created = LocalDateTime.now();

    public void addLineItem(Item item) {
        items.add(item);
        total += item.price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("--- Order ---");
        sb.append("\n");
        sb.append(String.format("Description: %s", this.description));
        sb.append("\n");
        sb.append("Items: ");
        sb.append("\n");
        this.items.forEach(item -> {
            sb.append(String.format("\t %s with price: %s", item.getName(), item.getPrice()));
            sb.append("\n");
        });
        sb.append(String.format("Payment method: %s", this.paymentMethod.getValue()));
        sb.append("\n");
        sb.append(String.format("Total: %s", this.total));
        sb.append("\n");

        return sb.toString();
    }
}
