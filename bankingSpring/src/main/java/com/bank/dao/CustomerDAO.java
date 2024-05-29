package com.bank.dao;

import org.hibernate.Session;	

import com.bank.entities.Customer;
import com.bank.utils.Utils;

import java.util.*;

public class CustomerDAO {
	private Session sn;
	
	public Customer getCustomer(int accno) {
		sn = Utils.openSession();
		
		Customer customer = (Customer) sn.get(Customer.class, accno);
		
		Utils.closeSession();
		return customer;
	}
	
	public double deposit(int accno, double amount) {
		sn = Utils.openSession();
		
		Customer customer = (Customer) sn.get(Customer.class, accno);

		customer.setBalance(customer.getBalance() + amount);
		customer.updateTransactions("Deposited " + amount + " on " + new Date());
		sn.save(customer);
		Utils.closeSession();
		return customer.getBalance();
	}
	
	public double withdraw(int accno, double amount) {
		sn = Utils.openSession();
		
		Customer customer = (Customer) sn.get(Customer.class, accno);
		
		if (amount > customer.getBalance()) {
			Utils.closeSession();
			return -1;
		}
		customer.setBalance(customer.getBalance() - amount);
		customer.updateTransactions("Withdrew " + amount + " on " + new Date());
		sn.save(customer);
		Utils.closeSession();
		return customer.getBalance();
	}
	
	public double transfer(int accno1, int accno2, double amount) {
		sn = Utils.openSession();
		
		Customer c1 = (Customer) sn.get(Customer.class, accno1);
		Customer c2 = (Customer) sn.get(Customer.class, accno2);
		
		if (c2 == null) {
			Utils.closeSession();
			return -2;
		}
		if (amount > c1.getBalance()) {
			Utils.closeSession();
			return -1;
		}
		c1.setBalance(c1.getBalance() - amount);
		c2.setBalance(c2.getBalance() + amount);
		c1.updateTransactions("Transferred " + amount + " to " + c2.getName() + " on " + new Date());
		c2.updateTransactions("Recieved " + amount + " from " + c1.getName() + " on " + new Date());
		sn.save(c1);
		sn.save(c2);
		Utils.closeSession();
		return c1.getBalance();
	}
	
	public String getStatement(int accno) {
		sn = Utils.openSession();
		
		Customer customer = (Customer) sn.get(Customer.class, accno);
		
		if (customer == null) {
			Utils.closeSession();
			return null;
		}
		
		String res = "";
		List<String> transactions = customer.getTransactions();
		
		for (int i = transactions.size() - 1; i >= 0; i--) {
			res += transactions.get(i) + "\n";
		}
		
		Utils.closeSession();
		return res;
	}
	
}
