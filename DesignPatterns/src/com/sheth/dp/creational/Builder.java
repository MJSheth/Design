package com.sheth.dp.creational;

/**
 * 
 * It is used to create complex object using simple objects and via step by step
 * approach
 *
 * Product: Complex object which we are trying to build
 * 
 * Builder: It's an abstract interface for creating parts of a Product object
 * 
 * ConcreteBuilder: It puts together all parts by implementing Builder interface
 * 
 * Director: It constructs Product object using Builder interface
 *
 */

public class Builder {
	public static void main(String args[]) {
		ThinCrustPizzaBuilder thinCrustPizzaBuilder = new ThinCrustPizzaBuilder();
		SpicyPizzaBuilder spicyPizzaBuilder = new SpicyPizzaBuilder();

		Waiter waiter = new Waiter();
		waiter.setPizzaBuilder(thinCrustPizzaBuilder);
		waiter.constructPizza();
		System.out.println(waiter.getPizza());

		waiter.setPizzaBuilder(spicyPizzaBuilder);
		waiter.constructPizza();
		System.out.println(waiter.getPizza());
	}
}

// Product
class Pizza {
	private String dough = "";
	private String sauce = "";
	private String topping = "";

	public String getDough() {
		return dough;
	}

	public void setDough(String dough) {
		this.dough = dough;
	}

	public String getSauce() {
		return sauce;
	}

	public void setSauce(String sauce) {
		this.sauce = sauce;
	}

	public String getTopping() {
		return topping;
	}

	public void setTopping(String topping) {
		this.topping = topping;
	}

	@Override
	public String toString() {
		return dough + "->" + sauce + "->" + topping;
	}
}

// Builder
abstract class PizzaBuilder {
	protected Pizza pizza;

	public Pizza getPizza() {
		return pizza;
	}

	public void CreateNewPizza() {
		pizza = new Pizza();
	}

	public abstract void buildDough();

	public abstract void buildSauce();

	public abstract void buildTopping();

}

// Concrete Builder 1
class ThinCrustPizzaBuilder extends PizzaBuilder {

	@Override
	public void buildDough() {
		pizza.setDough("Thin Crust");
	}

	@Override
	public void buildSauce() {
		pizza.setSauce("Mild");
	}

	@Override
	public void buildTopping() {
		pizza.setTopping("olive+pinapple");
	}

}

// Concrete Builder 2
class SpicyPizzaBuilder extends PizzaBuilder {

	@Override
	public void buildDough() {
		pizza.setDough("Pan Baked");
	}

	@Override
	public void buildSauce() {
		pizza.setSauce("Hot");
	}

	@Override
	public void buildTopping() {
		pizza.setTopping("Papperoni+Salami");
	}

}

// Director
class Waiter {
	private PizzaBuilder pizzaBuilder;

	public void setPizzaBuilder(PizzaBuilder pizzaBuilder) {
		this.pizzaBuilder = pizzaBuilder;
	}

	public Pizza getPizza() {
		return pizzaBuilder.getPizza();
	}

	public void constructPizza() {
		pizzaBuilder.CreateNewPizza();
		pizzaBuilder.buildDough();
		pizzaBuilder.buildSauce();
		pizzaBuilder.buildTopping();
	}
}