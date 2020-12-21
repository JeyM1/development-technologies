package com.pizzamaker.Products;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {
    @Test
    public void checkEquality() {
        Pineapple pn = new Pineapple(100);
        Pineapple pn2 = new Pineapple(100);

        Tomato tom = new Tomato(100);

        Assertions.assertEquals(pn, pn2);
        Assertions.assertNotEquals((Product) pn, (Product) tom);

        Cheese mozzarella = new Cheese(100, "mozzarella");
        Cheese mozzarella2 = new Cheese(150, "mozzarella");

        Assertions.assertNotEquals(mozzarella, mozzarella2);
        Assertions.assertEquals(mozzarella, new Cheese(100, "mozzarella"));
    }
}
