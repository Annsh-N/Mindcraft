package in.student;

public class Student {
	private String name;
	private int rollno;
	private double percentage;
	static int count = 0;
	
	public Student(String name, int rollno, double percentage) {
		super();
		this.name = name;
		this.rollno = rollno;
		this.percentage = percentage;
		count++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRollno() {
		return rollno;
	}

	public void setRollno(int rollno) {
		this.rollno = rollno;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	
	public static void getCount() {
		System.out.println("Number of Students is: " + count);
	}
	
	public String toString() {
		return "Name: " + name + "\nRoll No.: " + rollno + "\nPercentage: " + percentage;
	}
	
}
