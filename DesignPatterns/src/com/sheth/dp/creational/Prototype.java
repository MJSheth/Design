package com.sheth.dp.creational;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * It is used to create duplicate(clone) object while keeping performance in
 * mind. We use it in scenarios where object creation is resource intensive
 * 
 *
 * Client: Creates new object by asking Prototype to clone itself
 * 
 * Prototype: Interface to clone itself
 * 
 * ConcretePrototype: Implements operation to clone itself
 */

public class Prototype {
	public static void main(String args[]) throws CloneNotSupportedException {
		Employees e1 = new Employees();
		e1.loadData();

		System.out.println("e1:" + e1.getEmployees());

		Employees e2 = (Employees) e1.clone();
		e2.getEmployees().add("e2");
		System.out.println("e2:" + e2.getEmployees());

		Employees e3 = (Employees) e1.clone();
		e3.getEmployees().remove("CFO");
		System.out.println("e3:" + e3.getEmployees());
	}
}

class Employees implements Cloneable {
	private List<String> empList;

	Employees() {
		empList = new ArrayList<String>();
	}

	Employees(List<String> empList) {
		this.empList = empList;
	}

	public List<String> getEmployees() {
		return empList;
	}

	public void loadData() {
		empList.add("CEO");
		empList.add("CTO");
		empList.add("CFO");
	}

	public Object clone() throws CloneNotSupportedException {
		List<String> empList = new ArrayList<String>();
		for (String s : this.getEmployees()) {
			empList.add(s);
		}
		return new Employees(empList);
	}
}
