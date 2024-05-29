package in.mindcraft.pojo;

public class Laptop {
	private int pid;
	private String make;
	private double cost;
	
	public Laptop(int pid, String make, double cost) {
		super();
		this.pid = pid;
		this.make = make;
		this.cost = cost;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getMake() {
		return make;
	}

	public void setName(String make) {
		this.make = make;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public String toString() {
		return "PID: " + pid + "\nMake: " + make + "\nCost: " + cost + "\n";
	}
}
