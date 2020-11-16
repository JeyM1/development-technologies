package com.pizzamaker;

import com.pizzamaker.PizzaExceptions.IncompatibleComponentException;
import com.pizzamaker.Products.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import org.mockito.Mockito;
import org.mockito.Mockito.*;

public class PizzaTest {
    private Pizza basicPizza;
    private IncompatibleProductsChain basicIncompatibleProducts;

    @BeforeEach
    public void init() {
        basicIncompatibleProducts = new IncompatibleProductsChain();
        basicPizza = new Pizza(new ArrayList<>(), "basicPizza", PizzaSize.MEDIUM, basicIncompatibleProducts);
        try {
            basicPizza.addPizzaComponent(new Dough(100))
                    //.addPizzaComponent(new Cheese(50, "mozzarella"))
                    .addPizzaComponent(new Mushrooms.Mushroom(5))
                    .addPizzaComponent(new Pepper(10));
        } catch (IncompatibleComponentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkEquality() {
        Pizza secondBasicPizza = new Pizza(new ArrayList<>(), "basicPizza", PizzaSize.MEDIUM, basicIncompatibleProducts);
        try {
            secondBasicPizza.addPizzaComponent(new Dough(100))
                    .addPizzaComponent(new Cheese(50, "mozzarella"))
                    .addPizzaComponent(new Mushrooms.Mushroom(5))
                    .addPizzaComponent(new Pepper(10));
        } catch (IncompatibleComponentException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(basicPizza, secondBasicPizza);
    }

    @Test
    public void testAddCommonComponent() {
        final int prevLength = basicPizza.getComponents().size();
        final int prevCount = basicPizza.getActualComponentsCount();
        final int prevMass = basicPizza.getTotalMass();
        Cheese component = Mockito.mock(Cheese.class);
        Mockito.when(component.mass()).thenReturn(20);
        Mockito.when(component.count()).thenReturn(1);
        Mockito.when(component.getName()).thenReturn("cute cheese");

        try {
            basicPizza.addPizzaComponent(component);
        } catch (IncompatibleComponentException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(prevLength + 1, basicPizza.getComponents().size());
        Assertions.assertEquals(prevCount + 1, basicPizza.getActualComponentsCount());
        Assertions.assertEquals(prevMass + 20, basicPizza.getTotalMass());

        Mockito.verify(component).mass();
        Mockito.verify(component).count();
    }

    @Test
    public void testAddMushroomsComponent() {
        final int prevLength = basicPizza.getComponents().size();
        final int prevCount = basicPizza.getActualComponentsCount();
        try {
            basicPizza.addPizzaComponent(new Mushrooms(5));
        } catch (IncompatibleComponentException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(prevLength + 1, basicPizza.getComponents().size());
        Assertions.assertEquals(prevCount + 5, basicPizza.getActualComponentsCount());
    }

    @Test
    public void testAddIncompatiblePizzaComponent() {
        final int prevCount = basicPizza.getActualComponentsCount();
        basicIncompatibleProducts.add(Map.entry(Mushrooms.Mushroom.class, Pineapple.class));
        Pineapple pineappleToAdd = new Pineapple(100);
        Exception exception = Assertions.assertThrows(IncompatibleComponentException.class, () -> {
           basicPizza.addPizzaComponent(pineappleToAdd);
        });
        Assertions.assertTrue(exception.getMessage().contains("Cannot add \"" + pineappleToAdd + "\""));
        Assertions.assertEquals(prevCount, basicPizza.getActualComponentsCount());
    }

}
