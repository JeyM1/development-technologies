package com.pizzamaker;

import java.util.Collection;
import java.util.Iterator;

public class Pizza {

    private final Collection<PizzaComponent> _components;
    private final String _name;
    private int _totalMass;

    public enum PizzaSize {
        SMALL, MEDIUM, LARGE, EXTRA_LARGE;
        @Override
        public String toString() {
            return super.toString().replace('_', ' ').toLowerCase();
        }
    }

    private final PizzaSize _size;

    public enum PizzaState {
        NOT_READY, COOKING, BAKING, READY, IN_DELIVERY;
        @Override
        public String toString() {
            return super.toString().replace('_', ' ').toLowerCase();
        }
    }

    private PizzaState _state;

    public Pizza(Collection<PizzaComponent> _components, String name, PizzaSize _size) {
        this._components = _components;
        this._name = name;
        this._size = _size;
        this._state = PizzaState.NOT_READY;
        this._totalMass = 0;
    }

    private void changePizzaState(PizzaState state) {
        this._state = state;
        System.out.println("Pizza \"" + _name + "\" changed state to: " + this._state);
    }

    public void cook()  {
        changePizzaState(PizzaState.COOKING);
        for (Iterator<PizzaComponent> it = _components.iterator(); it.hasNext(); ) {
            PizzaComponent cmp = it.next();
            cmp.cook();
        }
        changePizzaState(PizzaState.BAKING);
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            System.out.println("Something went wrong...");
            System.exit(1);
        }
        System.out.println("...Done");
        changePizzaState(PizzaState.READY);
    }

    public Pizza addPizzaComponent(PizzaComponent component) {
        this._components.add(component);
        this._totalMass += component.mass();
        return this;
    }

    public void startDelivery() {
        changePizzaState(PizzaState.IN_DELIVERY);
        System.out.println("Have a wonderful day! :)");
    }

    public void pizzaInfo() {
        System.out.println(" --- Pizza Info ---");
        System.out.println("Pizza name: " + _name);
        System.out.println("Pizza size: " + _size);
        System.out.println("Pizza components:");
        int count = 0;
        for (Iterator<PizzaComponent> it = _components.iterator(); it.hasNext(); ) {
            PizzaComponent cmp = it.next();
            System.out.println(" - " + cmp + ": total count=" + cmp.count() + "; total mass=" + cmp.mass());
            count += cmp.count();
        }
        System.out.println("Total count: " + count + " components");
        System.out.println("Total pizza mass: " + _totalMass + " grams");
        System.out.println("Current pizza state: " + _state);
    }
}
