package com.pizzamaker.Products;

import com.pizzamaker.PizzaComponent;

public abstract class Product implements PizzaComponent {
    protected final Integer _mass;

    public Product(Integer _mass) {
        this._mass = _mass;
    }

    public Integer get_mass() {
        return _mass;
    }
}
