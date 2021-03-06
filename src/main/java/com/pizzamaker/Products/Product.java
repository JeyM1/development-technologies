package com.pizzamaker.Products;

import com.pizzamaker.PizzaComponent;

import java.util.Objects;

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
        return Objects.hash(_mass);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(_mass, product._mass);
    }
}
