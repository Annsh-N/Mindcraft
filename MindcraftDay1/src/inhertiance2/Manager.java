package inhertiance2;

public class Manager extends Employee{
	double petrol;
	double food;
	double other;
	
	public Manager(String name, int id, double salary) {
		super(name, id, salary);
		petrol = 0.08*salary;
		food = 0.12*salary;
		other = 0.04*salary;
	}
	
	public double calculateGrossSalary() {
		return salary + petrol + food + other;
	}
	
	public double calculateNetSalary() {
		return calculateGrossSalary() - 0.125*salary;
	}
	
	public void display() {
		System.out.println("Name: " + name);
		System.out.println("Employee ID: " + id);
		System.out.println("Base Salary: " + salary);
		System.out.println("Food Allowance: " + food);
		System.out.println("Petrol Allowance: " + petrol);
		System.out.println("Other Allowance: " + other);
		System.out.println("Gross Salary: " + calculateGrossSalary());
		System.out.println("Net Salary: " + calculateNetSalary());
	}
}
