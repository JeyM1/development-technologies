package com.pizzamaker.Products;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TomatoSauceTest {
    @Test
    public void addToTomatoSauce() {
        TomatoSauce tomatoSauce = new TomatoSauce();
        Assertions.assertEquals(0, tomatoSauce.mass());
        tomatoSauce.addTomatoToSauce(new Tomato(100));
        Assertions.assertEquals(100, tomatoSauce.mass());
        Assertions.assertEquals(1, tomatoSauce.getTotalTomatoesCount());
    }

}
