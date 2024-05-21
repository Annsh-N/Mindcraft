package com.mindcraft.pack2;

public class Batch {
	private String courseName;
	private int batchStrength;
	
	public Batch(String courseName, int batchStrength) {
		this.courseName = courseName;
		this.batchStrength = batchStrength;
	}
	
	public void display() {
		System.out.println("Course Name: " + courseName + "\nBatch Strength: " + batchStrength + "\n");
	}
}
