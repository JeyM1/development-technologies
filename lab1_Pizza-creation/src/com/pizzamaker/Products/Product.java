package com.pizzamaker.Products;

import com.pizzamaker.PizzaComponent;

public abstract class Product implements PizzaComponent {
    protected Integer _mass;

    public Product(Integer _mass) {
        this._mass = _mass;
    }

    @Override
    public int count() {
        return 1;
    }

    @Override
    public int mass() {
        return _mass;
    }

    @Override
    public int hashCode() {
        return _mass.hashCode() + this.toString().hashCode();
    }
}
