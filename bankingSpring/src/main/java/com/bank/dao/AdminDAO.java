package com.bank.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.bank.entities.Customer;
import com.bank.utils.Utils;

public class AdminDAO {
	Session sn;
	
	
	public boolean addCustomer(Customer customer) {
		sn = Utils.openSession();
	
		Customer c = (Customer) sn.get(Customer.class, customer.getAccNo());
		if (c != null) {
			Utils.closeSession();
			return false;
		}
		sn.save(customer);
		Utils.closeSession();
		return true;
	}
	
	public Customer searchByNo(int accno) {
		sn = Utils.openSession();
		
		Query q = sn.createQuery("from Customer where accno =: x");
		q.setParameter("x", accno);
		Customer customer = (Customer) q.uniqueResult();
		
		Utils.closeSession();
		return customer;
	}
	
	public List<Customer> searchByName(String name) {
		sn = Utils.openSession();
		
		Query q = sn.createQuery("from Customer where name =: x");
		q.setParameter("x", name);
		List<Customer> customers = q.list();
		
		Utils.closeSession();
		return customers;
	}
	
	public boolean modify(int accno, String newName, double newBalance) {
		sn = Utils.openSession();
		
		Query q = sn.createQuery("update Customer set name =: n, balance =: b where accno =: x");
		q.setParameter("n", newName);
		q.setParameter("b", newBalance);
		q.setParameter("x", accno);
		int n = q.executeUpdate();
		Utils.closeSession();
		if (n == 0) {
			return false;
		}
		return true;
	}
	
	public boolean delete(int accno) {
		sn = Utils.openSession();
		
		
		Query q = sn.createQuery("delete from Customer where accno =: x");
		q.setParameter("x", accno);
		int n = q.executeUpdate();
		Utils.closeSession();
		if (n == 0) {
			return false;
		}
		return true;
	}	
}
