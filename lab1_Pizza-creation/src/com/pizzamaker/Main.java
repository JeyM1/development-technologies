package com.pizzamaker;

import com.pizzamaker.PizzaExceptions.AlreadyCookedException;
import com.pizzamaker.PizzaExceptions.IncompatibleComponentException;
import com.pizzamaker.Products.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Adding incompatible products
        IncompatibleProductsChain mainIncompatibleProducts = new IncompatibleProductsChain();
        mainIncompatibleProducts.add(Map.entry(Mushrooms.class, Pineapple.class));
        System.out.println(mainIncompatibleProducts);
        System.out.println(
                mainIncompatibleProducts.contains(Map.entry(Mushrooms.class, Pineapple.class)) ||
                mainIncompatibleProducts.contains(Map.entry(Pineapple.class, Mushrooms.class))
        );
        Pizza homePizza = new Pizza(new ArrayList<>(), "Home Pizza", PizzaSize.MEDIUM, mainIncompatibleProducts);
        try {
            homePizza.addPizzaComponent(new Dough(200))
                    .addPizzaComponent(new Cheese(50, "mozzarella"))
                    .addPizzaComponent(new Mushrooms(5))
                    .addPizzaComponent(new Cheese(20, "feta crumbled"))
                    .addPizzaComponent(new Sausage(150))
                    .addPizzaComponent(new Pepper(20))
                    .addPizzaComponent(new Pineapple(100));
        } catch (IncompatibleComponentException e) {
            System.err.println(e.getMessage());
        }

        try {
            homePizza.cook();
        } catch (AlreadyCookedException e) {
            e.printStackTrace();
        }
        homePizza.pizzaInfo();
        homePizza.startDelivery();
    }
}
