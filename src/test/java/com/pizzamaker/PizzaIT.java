package com.pizzamaker;

import com.pizzamaker.PizzaExceptions.IncompatibleComponentException;
import com.pizzamaker.Products.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class PizzaIT {
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
    public void AddComponent_AddCommonComponent() {
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
    public void AddComponent_AddMushroomsComponent() {
        final int prevLength = basicPizza.getComponents().size();
        final int prevCount = basicPizza.getActualComponentsCount();
        Mushrooms component = Mockito.mock(Mushrooms.class);
        Mockito.when(component.mass()).thenReturn(10);
        Mockito.when(component.count()).thenReturn(5);
        try {
            basicPizza.addPizzaComponent(component);
        } catch (IncompatibleComponentException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(prevLength + 1, basicPizza.getComponents().size());
        Assertions.assertEquals(prevCount + 5, basicPizza.getActualComponentsCount());

        Mockito.verify(component).count();
    }
}
