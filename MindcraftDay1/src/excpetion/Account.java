package excpetion;

public class Account {
	private double balance;
	private static final Object obj = new Object();
	
	public void deposit(double amount) {
		synchronized(obj) {
			balance += amount;
			System.out.println(amount + " deposited succesfully");
			System.out.println("New Balance is " + balance);
		}
	}
	
	public void withdraw(double amount) throws OverLimit, InsufficientBalance{
		synchronized(obj) {
			if (amount > 15000) {
				throw new OverLimit("Amount exceeds 15000 limit");
			}
			else if (amount > balance) {
				throw new InsufficientBalance("Insufficient Balance");
			}
			else {
				balance -= amount;
				System.out.println(amount + " withdrawn succesfully");
				System.out.println("New Balance is " + balance);
			}
		}
	}
	
	public static void main(String[] args) {
		Account a = new Account();
		
		a.deposit(10000);
		
		try {
			a.withdraw(14000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
