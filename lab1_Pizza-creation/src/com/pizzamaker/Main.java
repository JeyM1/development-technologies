package com.pizzamaker;

import com.pizzamaker.Products.Cheese;
import com.pizzamaker.Products.Dough;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		ArrayList<PizzaComponent> components = new ArrayList<PizzaComponent>();
		components.add(new Dough(10));
		components.add(new Cheese(100, "mozzarella"));
		components.add(new Cheese(100, "asdasd"));
		components.add(new Cheese(100, "asdasd"));
		components.add(new Cheese(100, "adsasdsad"));
		Pizza sirnaya = new Pizza(components, Pizza.PizzaSize.LARGE);
		sirnaya.cook();
	}
}
