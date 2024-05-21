package inheritance;

public class WageEmployee extends Employee{
	int hours;
	double rate;
	
	public WageEmployee(String name, int id, String dob, int hours, double rate) {
		super(name, id, dob);
		this.hours = hours;
		this.rate = rate;
	}
	
	public void display() {
		super.display();
		System.out.println("Salary: " + hours*rate + "\n");
		
	}
}
