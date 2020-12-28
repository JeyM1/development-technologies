package com.pizzamaker;

import com.pizzamaker.PizzaExceptions.IncompatibleComponentException;
import com.pizzamaker.Products.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PizzaTest {
    private Pizza basicPizza;
    private IncompatibleProductsChain basicIncompatibleProducts;

    @BeforeEach
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
        Assertions.assertEquals(basicPizza, secondBasicPizza);
    }

    @Test
    public void AddComponent_AddIncompatiblePizzaComponent() {
        final int prevCount = basicPizza.getActualComponentsCount();
        basicIncompatibleProducts.add(Map.entry(Mushrooms.Mushroom.class, Pineapple.class));
        Pineapple pineappleToAdd = new Pineapple(100);
        Exception exception = Assertions.assertThrows(IncompatibleComponentException.class, () -> {
            basicPizza.addPizzaComponent(pineappleToAdd);
        });
        Assertions.assertTrue(exception.getMessage().contains("Cannot add \"" + pineappleToAdd + "\""));
        Assertions.assertEquals(prevCount, basicPizza.getActualComponentsCount());
    }

    @Test
    public void find_alwaysFalseCallback() {
        Assertions.assertNull(basicPizza.find((component -> false)).orElse(null));
    }

    @Test
    public void find_findByComponentMass() {
        Predicate<PizzaComponent> cb = (component -> component.mass() == 50);
        Optional<PizzaComponent> expected = basicPizza
                .getComponents()
                .stream()
                .filter(cb)
                .findFirst();
        Optional<PizzaComponent> actual = basicPizza.find(cb);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAll_alwaysFalseCallback() {
        Assertions.assertEquals(0, basicPizza.findAll((component -> false)).size());
    }

    @Test
    public void findAll_findByComponentMass() {
        Predicate<PizzaComponent> cb = (component -> component.mass() >= 50);
        ArrayList<PizzaComponent> expected = basicPizza
                .getComponents()
                .stream()
                .filter(cb)
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<PizzaComponent> actual = basicPizza.findAll(cb);
        Assertions.assertEquals(expected, actual);
    }

}
