package com.pizzamaker.PizzaExceptions;

public class AlreadyCookedException extends Throwable {
    public AlreadyCookedException() {
        super("This pizza is already been cooked!");
    }
}
