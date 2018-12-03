package com.sheth.dp.structural;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * The intent of this pattern is to compose objects into tree structure to
 * represent part-whole hierarchies.
 * 
 * It lets client treat individual objects and composition objects uniformly.
 * 
 * E.g. Tree Structure which has folders which contains other folders as well as
 * leaf nodes called files. All the objects, individual(files) and
 * composition(folders) have common functionality like move, rename, delete etc
 *
 *
 * Component: Abstraction for leafs and composites.
 * 
 * Leaf: They don't have any children. They implement the functionalities
 * defined by Component interface
 * 
 * Composite: Stores child components in addition to implementing
 * functionalities defined by Component interface
 * 
 * Client: Manipulates objects in the hierarchy using component interface
 */

// Client
public class Composite {
	public static void main(String args[]) {
		Developer dev1 = new Developer("101", "Chirag", "Software Developer 1");
		Developer dev2 = new Developer("102", "Rakesh", "Software Developer 3");
		EmployeeDirectory devDir = new EmployeeDirectory();
		devDir.addEmployee(dev1);
		devDir.addEmployee(dev2);

		Manager manager1 = new Manager("1001", "Nikhil", "Software Development Manager 2");
		Manager manager2 = new Manager("1002", "Andy", "Software Development Manager 3");
		EmployeeDirectory managerDir = new EmployeeDirectory();
		managerDir.addEmployee(manager1);
		managerDir.addEmployee(manager2);

		EmployeeDirectory employees = new EmployeeDirectory();
		employees.addEmployee(devDir);
		employees.addEmployee(managerDir);
		employees.showEmployeeDetails();

	}
}

// Component
interface Employee {
	public void showEmployeeDetails();
}

// Leaf
class Developer implements Employee {

	String empName;
	String empId;
	String position;

	Developer(String empId, String empName, String position) {
		this.empId = empId;
		this.empName = empName;
		this.position = position;
	}

	public void showEmployeeDetails() {
		System.out.println("EmpId:" + empId + " EmpName:" + empName + " Position:" + position);
	}

}

// Leaf
class Manager implements Employee {

	String empName;
	String empId;
	String position;

	Manager(String empId, String empName, String position) {
		this.empId = empId;
		this.empName = empName;
		this.position = position;
	}

	public void showEmployeeDetails() {
		System.out.println("EmpId:" + empId + " EmpName:" + empName + " Position:" + position);
	}

}

// Composite
class EmployeeDirectory implements Employee {

	List<Employee> employees = new ArrayList<Employee>();

	public void addEmployee(Employee emp) {
		employees.add(emp);
	}

	public void removeEmployee(Employee emp) {
		employees.remove(emp);
	}

	public void showEmployeeDetails() {
		for (Employee e : employees) {
			e.showEmployeeDetails();
		}
	}
}