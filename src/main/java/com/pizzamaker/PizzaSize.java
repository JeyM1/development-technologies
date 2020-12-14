package com.pizzamaker;

public enum PizzaSize {
    SMALL, MEDIUM, LARGE, EXTRA_LARGE;

    @Override
    public String toString() {
        return super.toString().replace('_', ' ').toLowerCase();
    }
}
