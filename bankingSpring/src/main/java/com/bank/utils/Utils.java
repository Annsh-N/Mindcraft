package com.bank.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Utils {
	
	private static SessionFactory factory;
	private static Session session;
	
	public static Session openSession() {
		factory = new Configuration().configure().buildSessionFactory();
		session = factory.openSession();
		session.beginTransaction();
		return session;
	}
	
	public static void closeSession() {
		session.getTransaction().commit();
		session.close();
		factory.close();
	}
}


