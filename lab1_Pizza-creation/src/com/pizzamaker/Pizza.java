package com.pizzamaker;

import java.util.Collection;
import java.util.Iterator;

public class Pizza {


    private final Collection<PizzaComponent> _components;

    public enum PizzaSize {
        SMALL, MEDIUM, LARGE, EXTRALARGE;
    }

    private final PizzaSize _size;

    public enum PizzaState {
        NOT_READY, COOKING, BAKING, READY, DELIVERING;
    }

    private final PizzaState _state;

    public Pizza(Collection<PizzaComponent> _components, PizzaSize _size) {
        this._components = _components;
        this._size = _size;
        this._state = PizzaState.NOT_READY;
    }


    public void cook() {
        for (Iterator<PizzaComponent> it = _components.iterator(); it.hasNext(); ) {
            PizzaComponent cmp = it.next();
            cmp.cook();
        }
    }
}
