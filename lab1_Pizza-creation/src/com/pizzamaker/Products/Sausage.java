package com.pizzamaker.Products;

public class Sausage extends Product {
    public Sausage(Integer _mass) {
        super(_mass);
    }

    @Override
    public void cook(int delay) {
        System.out.println("Added sausage weighting " + _mass + " grams.");
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "sausage";
    }
}
