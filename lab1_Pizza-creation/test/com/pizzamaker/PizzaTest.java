package com.pizzamaker;

import com.pizzamaker.PizzaExceptions.IncompatibleComponentException;
import com.pizzamaker.Products.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Objects;

public class PizzaTest {
    private Pizza basicPizza;
    private IncompatibleProductsChain basicIncompatibleProducts;

    @Before
    public void init() {
        basicIncompatibleProducts = new IncompatibleProductsChain();
        basicPizza = new Pizza(new ArrayList<>(), "basicPizza", PizzaSize.MEDIUM, basicIncompatibleProducts);
        try {
            basicPizza.addPizzaComponent(new Dough(100))
                    .addPizzaComponent(new Cheese(50, "mozzarella"))
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
        Assert.assertEquals(basicPizza, secondBasicPizza);
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

        Assert.assertEquals(prevLength + 1, basicPizza.getComponents().size());
        Assert.assertEquals(prevCount + 5, basicPizza.getActualComponentsCount());
    }

}
