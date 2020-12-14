package com.pizzamaker;

public enum PizzaState {
    NOT_READY, COOKING, BAKING, READY, IN_DELIVERY;

    public boolean isReady() {
        return this.compareTo(PizzaState.READY) >= 0;
    }

    @Override
    public String toString() {
        return super.toString().replace('_', ' ').toLowerCase();
    }
}
