package com.pizzamaker.Products;

public class Pepper extends Product {

    public Pepper(Integer _mass) {
        super(_mass);
    }

    @Override
    public void cook(int delay) {
        System.out.println("Added some pepper (" + _mass + " gram).");
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "pepper";
    }
}
