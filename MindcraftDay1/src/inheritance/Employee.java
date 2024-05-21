package inheritance;

public class Employee {
	String name;
	int id;
	String dob;
	
	public Employee(String name, int id, String dob) {
		this.name = name;
		this.id = id;
		this.dob = dob;
	}
	
	public void display() {
		System.out.println("Name: " + name + "\nEmployee ID: " + id + "\nDOB: " + dob + "\n");
	}
	
	
}
