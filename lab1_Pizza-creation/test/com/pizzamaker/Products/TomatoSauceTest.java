package com.pizzamaker.Products;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class TomatoSauceTest {
    @Test
    public void addToTomatoSauce() {
        TomatoSauce tomatoSauce = new TomatoSauce();
        Assertions.assertEquals(0, tomatoSauce.mass());
        tomatoSauce.addTomatoToSauce(new Tomato(100));
        Assertions.assertEquals(100, tomatoSauce.mass());
        Assertions.assertEquals(1, tomatoSauce.getTotalTomatoesCount());
    }

    @Test
    public void addMultipleTomatoes() {
        TomatoSauce tomatoSauce = new TomatoSauce();
        final Random rnd = new Random();

        int totalMass = 0;
        int totalCount = 50;

        for (int i = 0; i < totalCount; i++) {
            int mass = rnd.nextInt(500);
            tomatoSauce.addTomatoToSauce(new Tomato(mass));
            totalMass += mass;
        }

        Assertions.assertEquals(totalCount, tomatoSauce.getTotalTomatoesCount());
        Assertions.assertEquals(totalMass, tomatoSauce.mass());
    }
}
