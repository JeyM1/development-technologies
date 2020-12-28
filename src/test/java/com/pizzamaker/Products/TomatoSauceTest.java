package com.pizzamaker.Products;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TomatoSauceTest {
    @Test
    void checkEquality() {

        TomatoSauce ts1 = new TomatoSauce();
        TomatoSauce ts2 = new TomatoSauce();
        Tomato t1 = new Tomato(10);
        Tomato t2 = new Tomato(10);
        ts1.addTomatoToSauce(t1);
        ts2.addTomatoToSauce(t2);

        Assertions.assertEquals(ts1, ts2);

        ts1.addTomatoToSauce(t2);
        ts2.addTomatoToSauce(t1);

        Assertions.assertEquals(ts1, ts2);
    }
}
