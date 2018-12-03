package com.sheth.dp.structural;

/**
 * 
 * It is used to hide the complexities of the system and provide a simpler
 * interface for the client to access the system.
 * 
 * Provides a unified interface to a set of interfaces in a subsystem.
 *
 * Facade as the name suggests: Face of the building
 * 
 * E.g java.sql.Connection is the best example. We used JDBC connection
 * interface to connect to DB. Each DB vendor is free to choose their own
 * implementation and we don't have to worry about it
 * 
 * 
 */

public class Facade {
	public static void main(String args[]) {
		OrderFacade orderFacade = new OrderFacade();
		orderFacade.placeOrder("OR123132");
	}
}

// SubSystem for Inventory Process
class Inventory {
	public void updateInventory(String orderId) {
		// logic to update stock for the order
		System.out.println("Updating inventory for Order:" + orderId);
	}
}

// SubSystem for Payment Process
class Payment {
	public void processPayment(String orderId) {
		// logic to process payment
		System.out.println("Processing payment for Order:" + orderId);
	}
}

// Client facing interface to access the system
class OrderFacade {
	private Inventory inventory = new Inventory();
	private Payment payment = new Payment();

	public void placeOrder(String orderId) {
		inventory.updateInventory(orderId);
		payment.processPayment(orderId);
	}
}