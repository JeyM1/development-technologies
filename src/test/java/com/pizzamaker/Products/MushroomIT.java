package com.pizzamaker.Products;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MushroomIT {
    @Test
    void spyMushrooms_AddMushroomToMushrooms() {
        Mushrooms ms = Mockito.spy(new Mushrooms(0));
        Mushrooms.Mushroom m = new Mushrooms.Mushroom(10);
        ms.getMushrooms().add(m);

        Assertions.assertEquals(1, ms.count());
    }
}
