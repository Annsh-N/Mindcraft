package CollectionsAndGenerics;
import java.util.*;

public class Employee {
	private String name;
	private int id;
	private double salary;
	
	public Employee() {
		name = "Annsh";
		id = 1024;
		salary = 10000;
	}
	
	public Employee(String name, int id, double salary ) {
		this.name = name;
		this.id = id;
		this.salary = salary;
	}
	
	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String toString() {
		return "Name: " + name + "\nEmployee ID: " + id + "\nSalary: " + salary + "\n";
	}
	
	public static void main(String[] args) {
		 List<Employee> list = new ArrayList<>();
		 Scanner sc = new Scanner(System.in);
		 int input = 0;
		 
		 while(true) {
			 System.out.println("1. Insert Record");
			 System.out.println("2. Update Record");
			 System.out.println("3. Display All Records");
			 System.out.println("4. Exit");
			 
			 input = sc.nextInt();
			 sc.nextLine();
			 
			 switch(input) {
			 case 1:
				 System.out.println("Enter Employee Name: ");
				 String name = sc.nextLine();
				 System.out.println("Enter Employee ID: ");
				 int id = sc.nextInt();
				 sc.nextLine();
				 System.out.println("Enter Salary: ");
				 double salary = sc.nextDouble();
				 sc.nextLine();
				 
				 list.add(new Employee(name, id, salary));
				 break;
				 
			 case 2:
				 System.out.println("Enter Employee ID to Update Record: ");
				 double emp_id = sc.nextDouble();
				 sc.nextLine();
				 boolean updated = false;
				 for (Employee e : list) {
					 if (e.getId() == emp_id) {
						 System.out.println("Employee Details: " + e.toString());
						 System.out.println("Select Field to Update: ");
						 System.out.println("1. Name");
						 System.out.println("2. Employee ID");
						 System.out.println("3. Salary");
						 int choice = sc.nextInt();
						 sc.nextLine();
						 switch(choice) {
						 case 1:
							 System.out.println("Enter new name: ");
							 String newName = sc.nextLine();
							 e.setName(newName);
							 
						 case 2:
							 System.out.println("Enter new ID: ");
							 int newId = sc.nextInt();
							 sc.nextLine();
							 e.setId(newId);
							 
						 case 3:
							 System.out.println("Enter new Salary: ");
							 double newSalary = sc.nextDouble();
							 sc.nextLine();
							 e.setSalary(newSalary);
						 }
						 updated = true;
						 System.out.println("Record updated succesfully");		 
					 }
				 }
				 if (!updated) {
					 System.out.println("Employee not found");
				 }
				 break;
				 
			 case 3:
				 System.out.println("All records:");
				 for (Employee e : list) {
					 System.out.println(e.toString());
				 }
				 break;
				 
			 case 4:
				 System.exit(0);
			 }
		 } 
	}
}
