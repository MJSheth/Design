package com.sheth.dp.creational;

/**
 * It's called factory of factories. Used to create factory of related object
 * without exposing their classes
 * 
 * AbstractProduct: Interface of a type of Product.
 * 
 * ConcreteProduct: It has concrete product implementations. Product which we want to create
 * 
 * AbstractFactory:  Interface for the operation that creates abstract Product
 * 
 * ConcreteFactory: Implements operations to create products.
 * 
 * Client: Uses interfaces declared by AbstractFactory
 */

public class AbstractFactory {
	public static void main(String args[]) {
		String platform = "Windows";
		GUIBuilder builder = new GUIBuilder();
		AbstractWidgetFactory abstractFactory;
		if (platform.equalsIgnoreCase("windows")) {
			abstractFactory = new MSWindowWidgetFactory();
		} else {
			abstractFactory = new MacOSXWidgetFactory();
		}
		builder.buildWindow(abstractFactory);
	}
}

// Abstract Product
interface Window {
	public void setTitle(String text);

	public void paint(String color);
}

// Concrete Product1
class MSWindow implements Window {
	@Override
	public void setTitle(String text) {
		System.out.println("Setting MSWindow title to:" + text);
	}

	@Override
	public void paint(String color) {
		System.out.println("Coloring MSWindow with:" + color);
	}
}

// Concrete Product2
class MacOSXWindow implements Window {
	@Override
	public void setTitle(String text) {
		System.out.println("Setting MacOSX title to:" + text);
	}

	@Override
	public void paint(String color) {
		System.out.println("Coloring MacOSX with:" + color);
	}
}

// Abstract Factory
interface AbstractWidgetFactory {
	public Window createWindow();
}

// Concrete Factory 1 produces Concrete Product 1
class MSWindowWidgetFactory implements AbstractWidgetFactory {
	@Override
	public Window createWindow() {
		return new MSWindow();
	}
}

// Concrete Factory 2 produces Concrete Product 2
class MacOSXWidgetFactory implements AbstractWidgetFactory {
	@Override
	public Window createWindow() {
		return new MacOSXWindow();
	}
}

// Client
class GUIBuilder {
	public void buildWindow(AbstractWidgetFactory abstractFactory) {
		abstractFactory.createWindow().setTitle("New Window");
		abstractFactory.createWindow().paint("Red");
	}
}
