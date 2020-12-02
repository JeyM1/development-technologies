package com.pizzamaker;

import com.pizzamaker.PizzaExceptions.AlreadyCookedException;
import com.pizzamaker.PizzaExceptions.IncompatibleComponentException;
import com.pizzamaker.Products.IncompatibleProductsChain;

import java.util.*;

enum PizzaSize {
    SMALL, MEDIUM, LARGE, EXTRA_LARGE;

    @Override
    public String toString() {
        return super.toString().replace('_', ' ').toLowerCase();
    }
}

enum PizzaState {
    NOT_READY, COOKING, BAKING, READY, IN_DELIVERY;

    public boolean isReady() {
        return this.compareTo(PizzaState.READY) >= 0;
    }

    @Override
    public String toString() {
        return super.toString().replace('_', ' ').toLowerCase();
    }
}

public class Pizza {

    private final Collection<PizzaComponent> _components;
    private int _actualComponentsCount = 0;
    private final String _name;
    private int _totalMass;
    private IncompatibleProductsChain _incompatibleProductsChain;

    private final PizzaSize _size;

    private PizzaState _state;

    public Pizza(Collection<PizzaComponent> _components, String name, PizzaSize _size,
                 IncompatibleProductsChain incompatibleProductsChain) {
        this._components = _components;
        this._name = name;
        this._size = _size;
        this._state = PizzaState.NOT_READY;
        this._totalMass = 0;
        this._incompatibleProductsChain = incompatibleProductsChain;
    }

    private void changePizzaState(PizzaState state) {
        this._state = state;
        System.out.println("Pizza \"" + _name + "\" changed state to: " + this._state);
    }

    public interface FindInterface {
        boolean findCallback(PizzaComponent component);
    }

    /**
     * find - function: finds the first component, that meets callback
     *
     * @param cb - lambda callback for applying on each element
     * @return PizzaComponent if found, else null
     */
    public PizzaComponent find(FindInterface cb) {
        for (PizzaComponent comp : this._components) {
            if (cb.findCallback(comp))
                return comp;
        }
        return null;
    }

    /**
     * findAll - function: finds all components, that meets callback
     *
     * @param cb - lambda callback for applying on each element
     * @return ArrayList<PizzaComponent> - components that meets callback
     */
    public ArrayList<PizzaComponent> findAll(FindInterface cb) {
        ArrayList<PizzaComponent> components = new ArrayList<>();
        for (PizzaComponent comp : this._components) {
            if (cb.findCallback(comp))
                components.add(comp);
        }
        return components;
    }

    public void cook() throws AlreadyCookedException {
        if (this._state.isReady()) {
            throw new AlreadyCookedException();
        }
        changePizzaState(PizzaState.COOKING);
        Random rnd = new Random();

        Iterator<PizzaComponent> it = _components.iterator();
        while (it.hasNext()) {
            PizzaComponent cmp = it.next();
            cmp.cook(rnd.nextInt(500));
        }
        changePizzaState(PizzaState.BAKING);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Something went wrong...");
            System.exit(1);
        }
        System.out.println("...Done");
        changePizzaState(PizzaState.READY);
    }

    public Pizza addPizzaComponent(PizzaComponent component) throws IncompatibleComponentException {
        if (this._components.stream()
                .anyMatch(currComp ->
                        _incompatibleProductsChain.contains(Map.entry(currComp.getClass(), component.getClass())) ||
                                _incompatibleProductsChain.contains(Map.entry(component.getClass(), currComp.getClass()))
                )
        ) {
            throw new IncompatibleComponentException("Cannot add \"" + component + "\".");
        }
        this._components.add(component);
        this._totalMass += component.mass();
        this._actualComponentsCount += component.count();
        return this;
    }

    public Collection<PizzaComponent> getComponents() {
        return _components;
    }

    public int getActualComponentsCount() {
        return _actualComponentsCount;
    }

    public int getTotalMass() {
        return _totalMass;
    }

    public IncompatibleProductsChain getIncompatibleProductsChain() {
        return _incompatibleProductsChain;
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
        Iterator<PizzaComponent> it = _components.iterator();
        while (it.hasNext()) {
            PizzaComponent cmp = it.next();
            System.out.println(" - " + cmp + ": total count=" + cmp.count() + "; total mass=" + cmp.mass());
            count += cmp.count();
        }
        System.out.println("Total count: " + count + " components");
        System.out.println("Total pizza mass: " + _totalMass + " grams");
        System.out.println("Current pizza state: " + _state);
    }

    @Override
    public int hashCode() {
        return this._components.hashCode() + this._name.hashCode() + this._size.hashCode() + this._state.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return _totalMass == pizza._totalMass &&
                Objects.equals(_components, pizza._components) &&
                Objects.equals(_name, pizza._name) &&
                Objects.equals(_incompatibleProductsChain, pizza._incompatibleProductsChain) &&
                _size == pizza._size &&
                _state == pizza._state;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb
                .append("Pizza: ")
                .append(this._name)
                .append("\nmass:")
                .append(this._totalMass)
                .append("\nstate: ")
                .append(this._state)
                .append("\nsize: ")
                .append(this._size)
                .append("\ncomponents: ");
        this._components.forEach(comp -> sb
                .append("\n  - ")
                .append(comp));
        return sb.toString();
    }
}
