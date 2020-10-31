package com.pizzamaker;

import com.pizzamaker.Products.*;

import java.util.ArrayList;

public class Main {

	public static void main( String[] args ) {
		Pizza homePizza = new Pizza( new ArrayList<>(), "Home Pizza", PizzaSize.MEDIUM );
		homePizza.addPizzaComponent( new Dough( 200 ) )
				.addPizzaComponent( new Cheese( 50, "mozzarella" ) )
				.addPizzaComponent( new Mushrooms( 5 ) )
				.addPizzaComponent( new Cheese( 20, "feta crumbled" ) )
				.addPizzaComponent( new Sausage( 150 ) )
				.addPizzaComponent( new Pepper( 20 ) );
		homePizza.cook();
		homePizza.pizzaInfo();
		homePizza.startDelivery();
	}
}
