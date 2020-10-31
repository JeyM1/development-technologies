package com.pizzamaker.Products;

public class Pineapple extends Product {
    public Pineapple(Integer _mass) {
        super(_mass);
    }

    @Override
    public void cook(int delay) {
        System.out.println("Cutting and adding a pineapple with total mass of " + _mass + " grams.");
    }

    @Override
    public String toString() {
        return "pineapple";
    }
}
