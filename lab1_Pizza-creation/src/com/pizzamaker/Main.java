package com.pizzamaker;

import com.pizzamaker.PizzaExceptions.AlreadyCookedException;
import com.pizzamaker.PizzaExceptions.IncompatibleComponentException;
import com.pizzamaker.Products.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        // default equals (its bad I know it)
        IncompatibleComponentException e1 = new IncompatibleComponentException("test");
        IncompatibleComponentException e2 = new IncompatibleComponentException("test");
        System.out.println(e1.equals(e2));

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

//        try {
//            homePizza.addPizzaComponent(new Pineapple(100));
//        } catch (IncompatibleComponentException e) {
//            System.err.println(e.getMessage());
//        }

//        try {
//            homePizza.cook();
//        } catch (AlreadyCookedException e) {
//            e.printStackTrace();
//        }
//
//        homePizza.pizzaInfo();
//        homePizza.startDelivery();
//        System.out.println(homePizza);

        System.out.println("--------------------------");
        System.out.println("Functional testing: ");
        // find
        System.out.println("All cheese in homePizza: " + homePizza.findAll(component -> component instanceof Cheese));
        System.out.println("The most heavy component (by mass): " +
                homePizza
                        .getComponents()
                        .stream()
                        .reduce(homePizza.getComponents().stream().findFirst().get(),
                                (component, heaviest) -> {
                                    if (heaviest.mass() < component.mass())
                                        heaviest = component;
                                    return heaviest;
                                })
        );
        System.out.println("Average mass of components: " +
                homePizza
                        .getComponents()
                        .stream()
                        .mapToInt(comp -> comp.mass())
                        .average()
                        .getAsDouble()
        );

        Map<String, List<PizzaComponent>> mappedComponents = homePizza
                .getComponents()
                .stream()
                .collect(Collectors.groupingBy(component -> component.mass() > 50 ? "heavy" : "light"));
        System.out.println("Mapped components: ");
        System.out.println(mappedComponents);

        Double averageMushroomsMass = homePizza
                .getComponents()
                .stream()
                .flatMap(component -> component instanceof Mushrooms ? ((Mushrooms)component).getMushrooms().stream() : null)
                .mapToInt(comp -> comp.mass())
                .average()
                .getAsDouble();
        System.out.println("Average Mushrooms mass: " + averageMushroomsMass);
    }
}
