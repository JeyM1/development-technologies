package com.pizzamaker;

import java.util.Collection;

public class Pizza {
    private Collection<PizzaComponent> _components;

    public enum PizzaSize {
        SMALL, MEDIUM, LARGE, EXTRALARGE;
    }
    private PizzaSize _size;

    public enum PizzaState {
        NOT_READY, COOKING, BAKING, READY, DELIVERING;
    }
    private PizzaState _state;

    public void addPizzaComponent() {
        
    }
}
