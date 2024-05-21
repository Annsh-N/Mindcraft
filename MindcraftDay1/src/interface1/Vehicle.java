package interface1;
import java.lang.Cloneable;

public class Vehicle implements Cloneable{
	String name;
	int number;
	double price;
	
	
	public Vehicle(String name, int number, double price) {
		this.name = name;
		this.number = number;
		this.price = price;
	}
	
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		
		Vehicle vehicle = (Vehicle) obj;
		return ((this.name.equals(vehicle.name)) && (this.number == vehicle.number) && (this.price == vehicle.price));
	}
	
	public int hashCode() {
		return this.number;
	}
	
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public static void main(String[] args)  throws CloneNotSupportedException {
		Vehicle a = new Vehicle("Ford", 25, 20000);
		Vehicle b = (Vehicle)a.clone();
		
		System.out.println(a.equals(b));
	}
}
