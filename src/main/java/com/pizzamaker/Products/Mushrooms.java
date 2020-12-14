package com.pizzamaker.Products;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Mushrooms extends Product {

    public static class Mushroom extends Product {
        public Mushroom(Integer _mass) {
            super(_mass);
        }

        @Override
        public void cook(int delay) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Just added a single mushroom weighing " + _mass + " grams.");
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

    public ArrayList<Mushroom> getMushrooms() {
        return _mushrooms;
    }

    @Override
    public void cook(int delay) {
        System.out.println("Cooking " + _mushrooms.size() + " mushrooms with total mass of " + _mass + " gram.");
        for (Mushroom m : this._mushrooms) {
            m.cook(delay);
        }
    }

    @Override
    public int count() {
        return _mushrooms.size();
    }

    @Override
    public String toString() {
        return "mushrooms " + "(" + _mass + "g)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Mushrooms mushrooms = (Mushrooms) o;
        return Objects.equals(_mushrooms, mushrooms._mushrooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), _mushrooms);
    }
}
