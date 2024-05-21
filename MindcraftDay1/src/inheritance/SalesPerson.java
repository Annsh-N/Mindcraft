package inheritance;

public class SalesPerson extends WageEmployee{
	int sales;
	double commission;
	
	public SalesPerson(String name, int id, String dob, int hours, double rate, int sales, double commission) {
		super(name, id, dob, hours, rate);
		this.sales = sales;
		this.commission = commission;
	}
	
	public void display() {
		System.out.println("Name: " + name + "\nEmployee ID: " + id + "\nDOB: " + dob + "\n");
		System.out.println("Salary: " + (hours*rate + sales*commission) + "\n");
	}
	
	
}
