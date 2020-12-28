package com.pizzamaker;

import com.pizzamaker.PizzaExceptions.IncompatibleComponentException;
import com.pizzamaker.Products.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Map;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {

        // default equals (its bad I know it)
        IncompatibleComponentException e1 = new IncompatibleComponentException("test");
        IncompatibleComponentException e2 = new IncompatibleComponentException("test");
        logger.info(e1.equals(e2));

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
            logger.error(e.getMessage());
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
//        logger.info(homePizza);

        logger.info("--------------------------");
        logger.info("Functional testing: ");
        // find
        logger.info("Something with 50 grams mass: " + homePizza.find(comp -> comp.mass() == 50).get());
        logger.info("All cheese in homePizza: " + homePizza.findAll(component -> component instanceof Cheese));

        PizzaRepository pizzaRepository = new PizzaRepository(homePizza);

        logger.info("The most heavy component (by mass): " + pizzaRepository
                .getMostHeavyComponent()
                .orElse(null)
        );
        logger.info("Average mass of components: " +
                pizzaRepository
                        .getAverageMass()
                        .orElse(Double.NaN)
        );

        logger.info("Mapped components: ");
        logger.info(pizzaRepository
                .getMappedComponents(component -> component.mass() > 50 ? "heavy" : "light"));

        double averageMushroomsMass = pizzaRepository
                .getAllMushrooms()
                .mapToInt(comp -> comp.mass())
                .average()
                .orElse(Double.NaN);
        logger.info("Average Mushrooms mass: " + averageMushroomsMass);
    }
}
