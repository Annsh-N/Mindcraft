package CollectionsAndGenerics;
import java.util.*;

public class Student implements Comparable<Student>{
	private int rollno;
	private String name; 
	private double percentage; 
	private Set<String> skillset;
	
	public Student(int rollno, String name, double percentage, Set<String> skillset) {
		this.rollno = rollno;
		this.name = name;
		this.percentage = percentage;
		this.skillset = skillset;
	}
	
	public int compareTo(Student s) {
		double diff = percentage - s.getPercentage();
		if (diff == 0) {
			return 0;
		}
		
		if (diff > 0) {
			return 1;
		}
		return -1;
	}

	public int getRollno() {
		return rollno;
	}

	public void setRollno(int rollno) {
		this.rollno = rollno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public Set<String> getSkillset() {
		return skillset;
	}

	public void setSkillset(Set<String> skillset) {
		this.skillset = skillset;
	}
	
	public String toString() {
		return "Name: " + name + "\nRoll No: " + rollno + "\nPercentage: " 
				+ percentage + "\nSkillset: " + skillset;
	}
}

class PercCompare implements Comparator<Student> {
	public int compare(Student s1, Student s2) {
		double diff = s1.getPercentage() - s2.getPercentage();
		
		if (diff == 0) {
			return 0;
		}
		
		if (diff > 0) {
			return 1;
		}
		return -1;
	}
}
