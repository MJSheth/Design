package com.sheth.dp.structural;

/**
 * 
 * It is used to add additional responsibilities dynamically to an object
 *
 * Component: Interface for objects that can have responsibilities added to them
 * dynamically
 * 
 * ConcreteComponent: Implementation of Component. Object to which additional
 * responsibilities can be added
 * 
 * Decorator: Maintains reference to Component object and defines an interface
 * that conforms to Component interface
 * 
 * ConcreteDecorator: It extends the functionality of Component by adding State
 * or Behavior
 * 
 * It can be viewed as degenerate Composite with only one component (Composite
 * has all the components - employees. Here's it's only one component, IceCream.
 * Different IceCream can have different decorator)
 */

public class Decorator {
	public static void main(String args[]) {
		IceCream simpleIceCream = new SimpleIceCream();
		simpleIceCream.makeIceCream();

		IceCream nuttyIceCream = new NuttyDecorator(simpleIceCream);
		nuttyIceCream.makeIceCream();

		IceCream caramelIceCream = new CaramelDecorator(simpleIceCream);
		caramelIceCream.makeIceCream();
	}
}

// Component
interface IceCream {
	public void makeIceCream();
}

// ConcreteComponent
class SimpleIceCream implements IceCream {
	@Override
	public void makeIceCream() {
		System.out.print("Simple IceCream ");
	}
}

// Decorator
abstract class IceCreamDecorator implements IceCream {
	IceCream specialIceCream;

	IceCreamDecorator(IceCream iceCream) {
		this.specialIceCream = iceCream;
	}
}

// ConcreteDecorator
class NuttyDecorator extends IceCreamDecorator {

	NuttyDecorator(IceCream iceCream) {
		super(iceCream);
	}

	@Override
	public void makeIceCream() {
		System.out.println();
		specialIceCream.makeIceCream();
		addNuts();
	}

	private void addNuts() {
		System.out.print("with added Nuts");
	}

}

// ConcreteDecorator
class CaramelDecorator extends IceCreamDecorator {

	CaramelDecorator(IceCream iceCream) {
		super(iceCream);
	}

	@Override
	public void makeIceCream() {
		System.out.println();
		specialIceCream.makeIceCream();
		addCaramel();
	}

	private void addCaramel() {
		System.out.print("with added Caramel");
	}

}