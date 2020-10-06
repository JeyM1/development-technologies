package com.pizzamaker.Products;

public class Pepper extends Product {

	public Pepper( Integer _mass ) {
		super( _mass );
	}

	@Override
	public void cook() {
		System.out.println( "Added some pepper (" + _mass + " gram)." );
	}

	@Override
	public String toString() {
		return "pepper";
	}

}
