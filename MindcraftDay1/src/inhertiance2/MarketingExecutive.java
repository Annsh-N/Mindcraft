package inhertiance2;

public class MarketingExecutive extends Employee{
	double kilometers;
	double tour;
	double telephone;
	
	public MarketingExecutive(String name, int id, double salary, double kilometers) {
		super(name, id, salary);
		this.kilometers = kilometers;
		tour = 5*kilometers;
		telephone = 2000;	
	}
	
	public double calculateGrossSalary() {
		return salary + kilometers + tour + telephone;
	}
	
	public double calculateNetSalary() {
		return calculateGrossSalary() - 0.125*salary;
	}
	
	public void display() {
		System.out.println("Name: " + name);
		System.out.println("Employee ID: " + id);
		System.out.println("Base Salary: " + salary);
		System.out.println("Kilometers Travelled: " + kilometers);
		System.out.println("Tour Allowance: " + tour);
		System.out.println("Telephone Allowance: " + telephone);
		System.out.println("Gross Salary: " + calculateGrossSalary());
		System.out.println("Net Salary: " + calculateNetSalary());
	}

}
