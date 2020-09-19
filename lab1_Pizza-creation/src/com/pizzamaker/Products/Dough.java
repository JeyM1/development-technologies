package com.pizzamaker.Products;

public class Dough extends Product {
    public Dough(Integer _mass) {
        super(_mass);
    }

    @Override
    public void cook() {
        System.out.println("Making pizza dough.. Done");
    }

    @Override
    public String toString() {
        return "dough";
    }
}
