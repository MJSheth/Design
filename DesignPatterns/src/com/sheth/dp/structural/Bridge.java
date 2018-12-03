package com.sheth.dp.structural;

/**
 * 
 * It is used where we need to decouple abstraction from its implementation so
 * that two can vary independently
 *
 * Abstraction: Core of the bridge pattern. Contains reference to implementor
 *
 * Implementor: It defines interface for implementation classes. It doesn't have
 * to correspond directly to Abstraction and can be very different.
 *
 * AbstractionImpl: Implements the abstraction interface using a reference to an
 * object of type Implementor.
 * 
 * ConcreteImplementors: Actual implementation of Implementor
 */

public class Bridge {
	public static void main(String args[]) {
		Shape rectangle = new Rectangle(new Red());
		rectangle.applyColor();

		Shape square = new Square(new Green());
		square.applyColor();

	}
}

// Implementor
interface Color {
	public void applyColor();
}

class Red implements Color {
	@Override
	public void applyColor() {
		System.out.println("Red Color");
	}

}

class Green implements Color {
	@Override
	public void applyColor() {
		System.out.println("Green Color");
	}

}

// Abstraction
abstract class Shape {
	Color color;

	Shape(Color color) {
		this.color = color;
	}

	public abstract void applyColor();
}

class Rectangle extends Shape {
	Rectangle(Color color) {
		super(color);
	}

	@Override
	public void applyColor() {
		System.out.print("Rectangle with ");
		color.applyColor();
	}
}

class Square extends Shape {
	Square(Color color) {
		super(color);
	}

	@Override
	public void applyColor() {
		System.out.print("Square with ");
		color.applyColor();
	}
}