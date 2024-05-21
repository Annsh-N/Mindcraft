package com.mindcraft.pack1;

public class Student {
	private String name;
	private int rollno;
	
	public Student(String name, int rollno) {
		this.name = name;
		this.rollno = rollno;
	}
	
	public void display() {
		System.out.println("Name: " + name + "\nRoll No: " + rollno + "\n");
	}	
}
