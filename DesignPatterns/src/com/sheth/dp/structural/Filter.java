package com.sheth.dp.structural;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * It is used to filter set of objects using different criteria, chaining them
 * in decoupled way through logical operation
 *
 */
public class Filter {
	public static void main(String args[]) {
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("Robert", "Male", "Single"));
		persons.add(new Person("John", "Male", "Married"));
		persons.add(new Person("Laura", "Female", "Married"));
		persons.add(new Person("Diana", "Female", "Single"));
		persons.add(new Person("Mike", "Male", "Single"));
		persons.add(new Person("Bobby", "Male", "Single"));

		Criteria male = new CriteriaMale();
		Criteria female = new CriteriaFemale();
		Criteria single = new CriteriaSingle();
		Criteria singleMale = new AndCriteria(single, male);
		Criteria singleOrFemale = new OrCriteria(single, female);

		System.out.println("Males: ");
		printPersons(male.meetCriteria(persons));
		System.out.println("\nFemales: ");
		printPersons(female.meetCriteria(persons));
		System.out.println("\nSingle Males: ");
		printPersons(singleMale.meetCriteria(persons));
		System.out.println("\nSingle Or Females: ");
		printPersons(singleOrFemale.meetCriteria(persons));
	}

	public static void printPersons(List<Person> persons) {
		for (Person person : persons) {
			System.out.println("Person : [ Name : " + person.getName() + ", Gender : " + person.getGender()
					+ ", Marital Status : " + person.getMaritalStatus() + " ]");
		}
	}
}

// Object that is filtered via different criteria
class Person {
	private String name;
	private String gender;
	private String maritalStatus;

	Person(String name, String gender, String maritalStatus) {
		this.name = name;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

}

// Criteria Interface to filter objects
interface Criteria {
	List<Person> meetCriteria(List<Person> persons);
}

// Implementations to filter Person objects based on some filter criteria
class CriteriaMale implements Criteria {
	public List<Person> meetCriteria(List<Person> persons) {
		List<Person> males = new ArrayList<Person>();
		for (Person p : persons) {
			if (p.getGender().equalsIgnoreCase("Male")) {
				males.add(p);
			}
		}
		return males;
	}
}

class CriteriaFemale implements Criteria {
	public List<Person> meetCriteria(List<Person> persons) {
		List<Person> females = new ArrayList<Person>();
		for (Person p : persons) {
			if (p.getGender().equalsIgnoreCase("Female")) {
				females.add(p);
			}
		}
		return females;
	}
}

class CriteriaSingle implements Criteria {
	public List<Person> meetCriteria(List<Person> persons) {
		List<Person> singles = new ArrayList<Person>();
		for (Person p : persons) {
			if (p.getMaritalStatus().equalsIgnoreCase("Single")) {
				singles.add(p);
			}
		}
		return singles;
	}
}

class AndCriteria implements Criteria {
	Criteria criterias[];

	AndCriteria(Criteria... criterias) {
		this.criterias = criterias;
	}

	public List<Person> meetCriteria(List<Person> persons) {
		List<Person> andCriteriaPerson = persons;
		for (Criteria criteria : criterias) {
			andCriteriaPerson = criteria.meetCriteria(andCriteriaPerson);
		}
		return andCriteriaPerson;
	}
}

class OrCriteria implements Criteria {
	Criteria criterias[];

	OrCriteria(Criteria... criterias) {
		this.criterias = criterias;
	}

	public List<Person> meetCriteria(List<Person> persons) {
		List<Person> orCriteriaPerson = new ArrayList<Person>();
		for (Criteria criteria : criterias) {
			for (Person person : criteria.meetCriteria(persons)) {
				if (!orCriteriaPerson.contains(person)) {
					orCriteriaPerson.add(person);
				}
			}

		}
		return orCriteriaPerson;
	}
}
