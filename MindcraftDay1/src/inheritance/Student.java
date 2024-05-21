package inheritance;

public class Student {
	private String name;
	private int rollno;
	private Date dob;
	static int count = 0;
	
	public Student() {
		name = null;
		rollno = ++count;
		dob = null;
	}
	
	public Student(String name, String dob) {
		this.name = name;
		this.dob = new Date(dob);
		rollno = ++count;
	}
	
	public Student(String name, int day, int month, int year) {
		this.name = name;
		this.dob = new Date(day, month, year);
		rollno = ++count;
	}
	
}
