package interface1;

public class CktPlayer implements printable{
	String name;
	int runs;
	
	public void printDetails() {
		System.out.println("Name: " + name);
		System.out.println("Runs: " + runs);
	}
	
}
