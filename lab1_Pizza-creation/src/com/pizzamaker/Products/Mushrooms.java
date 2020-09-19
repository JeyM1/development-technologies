package com.pizzamaker.Products;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class Mushrooms extends Product {

    public static class Mushroom extends Product {
        public Mushroom(Integer _mass) {
            super(_mass);
        }

        @Override
        public void cook() {
            System.out.println("Just added a simple mushroom weighing " + _mass + " grams");
        }

        @Override
        public String toString() {
            return "single mushroom";
        }
    }

    private final ArrayList<Mushroom> _mushrooms;

    public Mushrooms(Integer mushroomsCount) {
        super(0);
        Random rnd = new Random();
        _mushrooms = new ArrayList<>();
        for (int i = 0; i < mushroomsCount; i++) {
            int mass = 10 + rnd.nextInt(90);
            _mushrooms.add(new Mushroom(mass));
            this._mass += mass;
        }
    }

    @Override
    public void cook() {
        System.out.println("Cooking " + _mushrooms.size() + " mushrooms with total mass of " + _mass + " gram.");
    }

    @Override
    public int count() {
        return _mushrooms.size();
    }

    @Override
    public String toString() {
        return "mushrooms";
    }
}
