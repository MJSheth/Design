package com.sheth.dp.structural;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * It is used to provide a placeholder for an object to control
 * references/access of it
 *
 * Subject: Interface implemented by RealSubject. It must also be implemented by
 * Proxy so that Proxy can be used by any place where RealSubject can be used
 * 
 * RealSubject: Real object that the Proxy represent
 * 
 * Proxy: Maintains reference that allows Proxy to access RealSubject. Also
 * implements the same interface as RealSubject so that it can be used in place
 * of RealSubject. Controls access to RealSubject and may be creation and
 * deletion based on the type of Proxy
 * 
 * Decorator pattern adds responsibility to an object while Proxy controls
 * access of an object.
 * 
 * Adapter Pattern implements different interface to the object it adapts where
 * as Proxy implements the same interface
 */
public class Proxy {
	public static void main(String args[]) {
		Internet internet = new ProxyInternet();
		try {
			internet.connectTo("www.google.com");
			internet.connectTo("www.abc.com");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

// Subject
interface Internet {
	public void connectTo(String host) throws Exception;
}

// RealSubject
class RealInternet implements Internet {
	@Override
	public void connectTo(String host) {
		System.out.println("Connecting to " + host);
	}
}

// Proxy
class ProxyInternet implements Internet {
	Internet realInternet;;
	private static List<String> bannedSites = new ArrayList<String>();
	static {
		bannedSites.add("www.abc.com");
		bannedSites.add("www.def.com");
		bannedSites.add("www.ghi.com");
		bannedSites.add("www.jkl.com");
		bannedSites.add("www.xyz.com");
	}

	@Override
	public void connectTo(String host) throws Exception {
		if (bannedSites.contains(host)) {
			throw new Exception("Access Denied:" + host + " is banned on this Internet");
		} else {
			if (realInternet == null) {
				realInternet = new RealInternet();
			}
			realInternet.connectTo(host);
		}
	}
}