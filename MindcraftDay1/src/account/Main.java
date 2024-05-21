package account;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AccountHolder[] arr = new AccountHolder[10];
		int count = 0;
		int input = 0;
		
		while (true) {
		
			System.out.println("1. Add record for account holder");
			System.out.println("2. Display details of all account holders");
			System.out.println("3. Deposit an amount into particular account");
			System.out.println("4. Withdraw an account from particular account");
			System.out.println("5. Exit");
			
			input = sc.nextInt();
			sc.nextLine();
			
			switch(input) {
			case 1: 
				if (count == 10) {
					System.out.println("All slots are full");
					break;
				}
				System.out.println("Enter Account Holder Name:");
				String name = sc.nextLine();
				System.out.println("Enter Account Number:");
				int number = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter Balance:");
				double balance = sc.nextDouble();
				
				arr[count] = new AccountHolder(number, name, balance);
				count++;
				break;
				
			case 2:
				System.out.println("All Account Details:");
				
				for (int i = 0; i < count; i++) {
					System.out.print((i+1) + ". ");
					System.out.println("Name: " + arr[i].getName());
					System.out.println("   Number: " + arr[i].getNumber());
					System.out.println();
				}
				break;
				
			case 3:
				boolean valid = false;
				System.out.println("Enter Account Number to Deposit to: ");
				int num = sc.nextInt();
				sc.nextLine();
				
				System.out.println("Enter amount to Deposit: ");
				double amount = sc.nextDouble();
				sc.nextLine();
				
				if (amount <= 0) {
					System.out.println("Not a valid amount.");
					break;
				}
				for (int i = 0; i < count; i++) {
					if (arr[i].getNumber() == num) {
						arr[i].deposit(amount);
						valid = true;
						break;
					}
				}
				if (valid) {
					System.out.println("Rs. " + amount + " deposited succesfully");
					break;
				} else {
					System.out.println("Account not found");
					break;
				}
				
			case 4:
				boolean validW = false;
				System.out.println("Enter Account Number to Withdraw from: ");
				int numW = sc.nextInt();
				sc.nextLine();
				
				System.out.println("Enter amount to Withdraw: ");
				double amountW = sc.nextDouble();
				sc.nextLine();
				
				if (amountW <= 0) {
					System.out.println("Not a valid amount.");
					break;
				}
				int i;
				for (i = 0; i < count; i++) {
					if (arr[i].getNumber() == numW) {
						if (amountW > arr[i].getBalance()) {
							System.out.println("Amount " + amountW + " is greater than balance " + arr[i].getBalance());
							break;
						}
						arr[i].withdraw(amountW);
						validW = true;
						break;
					}
				}
				if (validW) {
					System.out.println("Rs. " + amountW + " withdrawn succesfully");
					System.out.println("Current balance is Rs. " + arr[i].getBalance());
					break;
				} else {
					System.out.println("Account not found");
					break;
				}
				
			case 5:
				System.exit(0);
			}
		}
	}
}
