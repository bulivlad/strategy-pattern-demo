package io.dotinc.strategydemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author vladclaudiubulimac on 14/10/2020.
 */

@Data
@AllArgsConstructor
public class Item {
    public String name;
    public Double price;
}
