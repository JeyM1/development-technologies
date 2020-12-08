package com.pizzamaker;

import com.pizzamaker.Products.Mushrooms;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PizzaRepository {
    Pizza pizza;

    public PizzaRepository(Pizza pizza) {
        this.pizza = pizza;
    }

    public Optional<PizzaComponent> getMostHeavyComponent() {
        return pizza
                .getComponents()
                .stream()
                .max(Comparator.comparingInt(PizzaComponent::mass));
    }

    public OptionalDouble getAverageMass() {
        return pizza
                .getComponents()
                .stream()
                .mapToInt(comp -> comp.mass())
                .average();
    }

    public <K> Map<K, List<PizzaComponent>>
    getMappedComponents(Function<? super PizzaComponent, ? extends K> classifier) {
        return pizza
                .getComponents()
                .stream()
                .collect(Collectors.groupingBy(classifier));
    }

    public Stream<Mushrooms.Mushroom> getAllMushrooms() {
        return pizza
                .getComponents()
                .stream()
                .flatMap(component -> component instanceof Mushrooms ? ((Mushrooms) component).getMushrooms().stream() : null);
    }

}
