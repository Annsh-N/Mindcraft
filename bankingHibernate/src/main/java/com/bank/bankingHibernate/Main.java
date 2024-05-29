package com.bank.bankingHibernate;

import java.util.List;
import java.util.Scanner;

public class Main {
	
	private static int accNo;
	private static CustomerDAO cd = new CustomerDAO();
	private static AdminDAO ad = new AdminDAO();
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Choose Login: ");
		System.out.println("1. Administrator");
		System.out.println("2. Customer");
		int val1 = sc.nextInt();
		sc.nextLine();
		
		
		while (true) {
			switch(val1) {
			case 1:
				System.out.println("1. Add Customer");
				System.out.println("2. Search Customer by Account Number");
				System.out.println("3. Search Customer by Name");
				System.out.println("4. Modify Customer");
				System.out.println("5. Delete Account");
				System.out.println("6. Exit");
				int val2 = sc.nextInt();
				sc.nextLine();
				
				switch (val2) {
					case 1:
						addCustomer();
						break;
					case 2:
						searchByNo();
						break;
					case 3:
						searchByName();
						break;
					case 4:
						modify();
						break;
					case 5:
						deleteAcc();
						break;
					case 6:
						System.out.println("Have a good day!");
						System.exit(0);
						break;
					default:
						System.out.println("Invalid option. Try again");
				}
				break;
				
			case 2:
				System.out.println("Enter Account Number");
				accNo = sc.nextInt();
				sc.nextLine();
				Customer c = cd.verify(accNo);
				if (c == null) {
					accNo = 0;
					System.out.println("Invalid Account Number");
					break;
				}
				System.out.println("Login Successful!");
				while (true) {
					System.out.println("Welcome, " + c.getName());
					System.out.println();
					System.out.println("Choose Operation:");
					System.out.println("1. Deposit");
					System.out.println("2. Withdraw");
					System.out.println("3. Transfer to Account");
					System.out.println("4. Print Mini-Statement");
					System.out.println("5. Exit");
					int val3 = sc.nextInt();
					sc.nextLine();
					
					switch (val3) {
					case 1:
						deposit();
						break;
					case 2:
						withdraw();
						break;
					case 3:
						transfer();
						break;
					case 4:
						statement();
						break;
					case 5:
						System.out.println("Have a good day!");
						sc.close();
						System.exit(0);
						break;
					default:
						System.out.println("Invalid option. Try again");
						System.out.println();
					}
				}
				
			case 3:
				System.out.println("Have a good day!");
				sc.close();
				System.exit(0);
				break;
				
			default:
				System.out.println("Invalid option. Try again");
			}
		}
	}
	
	public static void addCustomer() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Account Number:");
		int accno = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Name:");
		String name = sc.nextLine();
		Customer customer = new Customer(accno, name, 0, null);
		if (ad.addCustomer(customer)) {
			System.out.println("Customer Added Successfully");
		}
		else {
			System.out.println("Account Number already exists!");
		}
		System.out.println();
	}
	
	public static void searchByNo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Account Number:");
		int accno = sc.nextInt();
		sc.nextLine();
		Customer customer = ad.searchByNo(accno);
		if (customer == null) {
			System.out.println("Customer not found!");
		} else {
			System.out.println("Customer found:");
			System.out.println(customer);
		}
		System.out.println();
	}
	
	public static void searchByName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Name:");
		String name = sc.nextLine();
		List<Customer> customers = ad.searchByName(name);
		if (customers == null) {
			System.out.println("No customers found!");
		} else {
			System.out.println("Customers found:");
			for (Customer c : customers) {
				System.out.println(c);
			}
		}
		System.out.println();
	}
	
	public static void modify() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Account Number to Modify:");
		int accno = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter New Name:");
		String name = sc.nextLine();
		System.out.println("Enter New Balance");
		double balance = sc.nextDouble();
		sc.nextLine();
		if (ad.modify(accno, name, balance)) {
			System.out.println("Customer modified successfully!");
		} else {
			System.out.println("Customer not found");
		}
		System.out.println();
	}
	
	public static void deleteAcc() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Account Number to Delete:");
		int accno = sc.nextInt();
		sc.nextLine();
		if (ad.delete(accno)) {
			System.out.println("Customer deleted successfully!");
		} else {
			System.out.println("Customer not found");
		}
		System.out.println();

	}
	
	public static void deposit() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Amount to Deposit:");
		Double amount = sc.nextDouble();
		sc.nextLine();
		if (amount <= 0) {
			System.out.println("Not a valid amount");
			System.out.println();

			return;
		}
		double newBalance = cd.deposit(accNo, amount);
		System.out.println("Amount deposited successfully!");
		System.out.println("New Balance = " + newBalance);
		System.out.println();

	}
	
	public static void withdraw() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Amount to Withdraw:");
		Double amount = sc.nextDouble();
		sc.nextLine();
		if (amount <= 0) {
			System.out.println("Not a valid amount");
			System.out.println();
			return;
		}
		double newBalance = cd.withdraw(accNo, amount);
		if (newBalance < 0) {
			System.out.println("Insufficient Balance");
		} else {
			System.out.println("Amount withdrawn successfully!");
			System.out.println("New Balance = " + newBalance);
		}
		System.out.println();
	}
	
	public static void transfer() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Account No. to Transfer to:");
		int accno = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Amount to Transfer:");
		Double amount = sc.nextDouble();
		sc.nextLine();
		if (amount <= 0) {
			System.out.println("Not a valid amount");
			System.out.println();
			return;
		}
		double newBalance = cd.transfer(accNo, accno, amount);
		if (newBalance == -2) {
			System.out.println("Account not found");
		}
		else if (newBalance == -1) {
			System.out.println("Insufficient Balance");
		} else {
			System.out.println("Amount transferred successfully!");
			System.out.println("New Balance = " + newBalance);
		}
		System.out.println();
	}
	
	public static void statement() {
		String transactions = cd.getStatement(accNo);
		if (transactions.isEmpty()) {
			System.out.println("No past transactions");
		}
		else {
			System.out.println("Here are your last 5 transactions:");
			System.out.println(transactions);
		}
		System.out.println();
	}

}
