package com.pizzamaker.Products;

import java.util.Objects;

public class Cheese extends Product {
    private final String _name;

    public Cheese(Integer _mass, String _name) {
        super(_mass);
        this._name = _name;
    }

    @Override
    public void cook(int delay) {
        System.out.println("Adding " + this._mass + " grams of \"" + this._name + "\" cheese.");
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "cheese";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cheese cheese = (Cheese) o;
        return Objects.equals(_name, cheese._name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), _name);
    }
}
