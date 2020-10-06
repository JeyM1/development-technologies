package com.pizzamaker.Products;

public class Sausage extends Product {
	public Sausage( Integer _mass ) {
		super( _mass );
	}

	@Override
	public void cook() {
		System.out.println( "Added sausage weighting " + _mass + " grams." );
	}

	@Override
	public String toString() {
		return "sausage";
	}
}
