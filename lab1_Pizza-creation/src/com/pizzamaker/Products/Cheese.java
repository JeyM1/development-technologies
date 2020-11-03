package com.pizzamaker.Products;

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
    public int hashCode() {
        return _name.hashCode() + _mass.hashCode() + this.toString().hashCode();
    }
}
