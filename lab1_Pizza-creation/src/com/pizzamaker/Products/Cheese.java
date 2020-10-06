package com.pizzamaker.Products;

public class Cheese extends Product {
	private final String _name;

	public Cheese( Integer _mass, String _name ) {
		super( _mass );
		this._name = _name;
	}

	@Override
	public void cook() {
		System.out.println( "Adding " + this._mass + " grams of \"" + this._name + "\" cheese." );
	}

	@Override
	public String toString() {
		return "cheese";
	}
}
