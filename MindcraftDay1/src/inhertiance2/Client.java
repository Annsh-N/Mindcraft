package inhertiance2;

public class Client {
	static void printObjects(Employee e) {
		e.display();
	}
	
	public static void main(String[] args) {
		Manager manager = new Manager("Annsh", 1024, 20000);
		MarketingExecutive marketer = new MarketingExecutive("annsh", 2048, 10000, 10);
		
		printObjects(manager);
		printObjects(marketer);
		
	}
}
