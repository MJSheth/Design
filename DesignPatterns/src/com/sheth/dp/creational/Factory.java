package com.sheth.dp.creational;

/**
 * It is used to create object without exposing creation logic to the client and
 * refers newly created object via common interface
 */

public class Factory {
	public static void main(String args[]) {
		Shape shape = ShapeFactory.getShape("circle");
		shape.draw();

		shape = ShapeFactory.getShape("square");
		shape.draw();

		shape = ShapeFactory.getShape("rectangle");
		shape.draw();
	}
}

interface Shape {
	public void draw();
}

class Square implements Shape {
	@Override
	public void draw() {
		System.out.println("Drawing Square");
	}
}

class Circle implements Shape {
	@Override
	public void draw() {
		System.out.println("Drawing Circle");
	}
}

class Rectangle implements Shape {
	@Override
	public void draw() {
		System.out.println("Drawing Rectangle");
	}
}

class NoShape implements Shape {
	@Override
	public void draw() {

	}
}

class ShapeFactory {
	public static Shape getShape(String type) {
		if (type.equalsIgnoreCase("Square")) {
			return new Square();
		} else if (type.equalsIgnoreCase("Circle")) {
			return new Circle();
		} else if (type.equalsIgnoreCase("Rectangle")) {
			return new Rectangle();
		} else {
			return new NoShape();
		}
	}
}
