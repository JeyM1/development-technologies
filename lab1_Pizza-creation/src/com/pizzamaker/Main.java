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

        Pizza homePizza = new Pizza(new ArrayList<>(), "Home Pizza", PizzaSize.MEDIUM, mainIncompatibleProducts);
        TomatoSauce homePizzaTomatoSauce = new TomatoSauce();
        homePizzaTomatoSauce.addTomatoToSauce(new Tomato(20));
        homePizzaTomatoSauce.addTomatoToSauce(new Tomato(35));

        try {
            homePizza.addPizzaComponent(new Dough(200))
                    .addPizzaComponent(new Cheese(50, "mozzarella"))
                    .addPizzaComponent(new Mushrooms(5))
                    .addPizzaComponent(new Cheese(20, "feta crumbled"))
                    .addPizzaComponent(new Sausage(150))
                    .addPizzaComponent(new Pepper(20))
                    .addPizzaComponent(homePizzaTomatoSauce);
        } catch (IncompatibleComponentException e) {
            System.err.println(e.getMessage());
        }

        try {
            homePizza.addPizzaComponent(new Pineapple(100));
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
        System.out.println(homePizza);
    }
}
