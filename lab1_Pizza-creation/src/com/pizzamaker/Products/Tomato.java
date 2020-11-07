package com.pizzamaker.Products;

public class Tomato extends Product {
    public Tomato(Integer _mass) {
        super(_mass);
    }

    @Override
    public void cook(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Cooking tomato (" + _mass + "grams).");
    }

    @Override
    public String toString() {
        return "tomato";
    }

}
