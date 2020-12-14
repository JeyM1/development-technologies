package com.pizzamaker.PizzaExceptions;

public class IncompatibleComponentException extends Exception {
    public IncompatibleComponentException(String message) {
        super("Incompatible PizzaComponent: " + message);
    }

    public IncompatibleComponentException(String message, Throwable cause) {
        super("Incompatible PizzaComponent: " + message, cause);
    }
}
