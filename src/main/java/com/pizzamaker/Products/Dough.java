package com.pizzamaker.Products;

public class Dough extends Product {
    public Dough(Integer _mass) {
        super(_mass);
    }

    @Override
    public void cook(int delay) {
        System.out.println("Making pizza dough..");
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Done");
    }

    @Override
    public String toString() {
        return "dough " + " (" + _mass + "g)";
    }

}
