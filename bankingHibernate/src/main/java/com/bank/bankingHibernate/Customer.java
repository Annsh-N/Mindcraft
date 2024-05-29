package com.bank.bankingHibernate;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {
	
	@Id
	private int accNo;
	private String name;
	private double balance;
	@ElementCollection
	private List<String> transactions;
	
	public Customer() {
		super();
	}

	public Customer(int accNo, String name, double balance, List<String> transactions) {
		super();
		this.accNo = accNo;
		this.name = name;
		this.balance = balance;
		this.transactions = transactions;
	}

	public int getAccNo() {
		return accNo;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public List<String> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<String> transactions) {
		this.transactions = transactions;
	}
	
	public void updateTransactions(String transaction) {
		if (transactions.size() == 5) {
			transactions.remove(0);
		}
		transactions.add(transaction);
	}
	
	public String toString() {
		return "Account Number: " + accNo + "\nName: " + name + "\nBalance: " + balance + "\n";
	}
}
