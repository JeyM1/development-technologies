package com.pizzamaker.Products;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class MushroomIT {
    @Test
    void spyMushrooms_AddMushroomToMushrooms() {
        ArrayList<Mushrooms.Mushroom> list = Mockito.spy(new ArrayList<>());
        Mushrooms ms = new Mushrooms(list);
        Mushrooms.Mushroom m = new Mushrooms.Mushroom(10);
        ms.addMushroom(m);

        Assertions.assertEquals(1, ms.count());
        Assertions.assertEquals(1, ms.getMushrooms().size());

        Mockito.verify(list).add(m);
    }
}
