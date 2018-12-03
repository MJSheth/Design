package com.sheth.dp.creational;

import java.io.Serializable;

/**
 * It uses single class which is responsible for creating own object and makes
 * sure that only one object is created
 * 
 * Check if instance is null and if it is then create it in synchronized
 * context. This is better than making method synchronized because you need to
 * create it only once and for all subsequent calls, you just have to return
 * existing instance which is read-only need of making method thread-safe for
 * subsequent calls which is unnecessary overhead
 *
 * If Singleton class implements Serializable interface than we must provide
 * implementation for readResolve() otherwise de-serialization will create two
 * different object
 * 
 */
public class Singleton {
	public static void main(String args[]) {

	}
}

class SingletonClass implements Serializable {
	private static SingletonClass instance = null;

	private SingletonClass() {

	}

	public static SingletonClass getInstance() {
		if (instance == null) {
			synchronized (SingletonClass.class) {
				if (instance == null) {
					instance = new SingletonClass();
				}
			}
		}
		return instance;
	}

	protected Object readResolve() {
		return getInstance();
	}
}