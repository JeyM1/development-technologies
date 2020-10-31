package com.pizzamaker.Products;

import com.pizzamaker.PizzaComponent;

import java.util.HashSet;
import java.util.Map;

public class IncompatibleProductsChain extends HashSet<Map.Entry<Class<? extends PizzaComponent>, Class<? extends PizzaComponent>>> {
}
