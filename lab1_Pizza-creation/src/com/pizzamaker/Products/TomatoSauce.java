package com.pizzamaker.Products;

import java.util.Objects;

public class TomatoSauce extends Product {
    private int _totalTomatoesCount = 0;

    public TomatoSauce() {
        super(0);
    }

    public void addTomatoToSauce(Tomato tomato) {
        ++this._totalTomatoesCount;
        this._mass += tomato._mass;
        tomato.cook(tomato._mass / 5);
    }

    @Override
    public void cook(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Cooking tomato sauce (" + _mass + "grams), total tomatoes count = " +
                _totalTomatoesCount + ".");
    }

    @Override
    public String toString() {
        return "Tomato sauce";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TomatoSauce that = (TomatoSauce) o;
        return _totalTomatoesCount == that._totalTomatoesCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), _totalTomatoesCount);
    }
}
