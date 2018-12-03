package com.sheth.dp.structural;

/**
 * 
 * It works as a bridge between two incompatible interfaces
 * 
 * Adapter lets classes work together, that could not otherwise because of
 * incompatible interfaces
 * 
 * Target: Interface that client expects
 * 
 * Adapter: Adapts interface Adaptee to Target interface
 * 
 * Adaptee: Existing interface that needs adapting
 * 
 * Client: Collaborates with objects of type Target
 *
 * Example: Using existing UK connector with Indian Socket which requires
 * Adapter to convert UK connector to Indian connector
 */

// Client
public class Adapter {
	public static void main(String args[]) {
		IndianElectricalSocket socket = new IndianElectricalSocket();

		UKConnector ukConnector = new UKConnector() {

			@Override
			public void provideElectricityForUKConnector() {
				System.out.println("Electical supply via UK Connector");
			}
		};
		IndianConnector adapter = new UKToIndianConnectorAdapter(ukConnector);
		socket.plugIn(adapter);
	}
}

// Required: Target
class IndianElectricalSocket {
	public void plugIn(IndianConnector connector) {
		connector.provideElectricityForIndianConnector();
	}
}

interface IndianConnector {
	public void provideElectricityForIndianConnector();
}

// Existing/Available: Adaptee
class UKElectricalSocket {
	public void plugIn(UKConnector connector) {
		connector.provideElectricityForUKConnector();
	}
}

interface UKConnector {
	public void provideElectricityForUKConnector();
}

// Adapter
class UKToIndianConnectorAdapter implements IndianConnector {

	UKConnector connector;

	UKToIndianConnectorAdapter(UKConnector connector) {
		this.connector = connector;
	}

	@Override
	public void provideElectricityForIndianConnector() {
		connector.provideElectricityForUKConnector();
	}

}