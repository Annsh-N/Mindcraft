package shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import shopping.pojo.Cart;
import shopping.pojo.Customer;
import shopping.pojo.Product;
import shopping.utils.DBUtils;

public class CartDAO {
	private Connection cn;
	private PreparedStatement pst1;
	private PreparedStatement pst2;
	private PreparedStatement pst3;
	private PreparedStatement pst4;
	private PreparedStatement pst5;
	private PreparedStatement pst6;
	
	public Cart displayCart(Customer customer) throws ClassNotFoundException, SQLException {
		cn = DBUtils.openConnection();
		
		Cart cart = new Cart(customer.getUsername());
		
		pst1 = cn.prepareStatement("SELECT * FROM Cart WHERE username = ?");
		pst1.setString(1, customer.getUsername());
		ResultSet rs = pst1.executeQuery();
		
		while(rs.next()) {
			Product product = new Product(rs.getInt("pid"), rs.getString("name"), rs.getDouble("price"), 
										  rs.getInt("quantity"), rs.getDouble("discount"));
			cart.addProduct(product);
		}
		
		DBUtils.closeConnection();
		return cart;
	}
	
	public double displayBill(Cart cart) {
		double bill = 0;
		
		for (Product p : cart.getCart()) {
			bill += p.getQuantity() * (p.getPrice() - p.getDiscount());
		}
		
		return bill;
	}
	
	public List<Product> payBill(Cart cart) throws ClassNotFoundException, SQLException {
		double bill = 0;
		List<Product> ordered = new ArrayList<>();
		
		
		cn = DBUtils.openConnection();
		
		for (Product p : cart.getCart()) {
			pst5 = cn.prepareStatement("SELECT * FROM Products WHERE pid = ?");
			pst5.setInt(1, p.getPid());
			ResultSet res = pst5.executeQuery();
			if (res.next()) {
				if (res.getInt("quantity") >= p.getQuantity()) {
					ordered.add(p);
				}
			}
		}
		
		for (Product p : ordered) {
			bill += p.getQuantity() * (p.getPrice() - p.getDiscount());
		}
		
		pst2 = cn.prepareStatement("SELECT * FROM Customers WHERE username = ?");
		pst2.setString(1, cart.getUsername());
		ResultSet rs = pst2.executeQuery();
		
		if (rs.next()) {
			if (rs.getDouble("wallet") < bill) {
				DBUtils.closeConnection();
				return null;
			}
		}
		
		for (Product p : ordered) {
			pst6 = cn.prepareStatement("UPDATE Products SET quantity = quantity - ? WHERE pid = ?");
			pst6.setInt(1, p.getQuantity());
			pst6.setInt(2, p.getPid());
			pst6.execute();
		}
		
		pst3 = cn.prepareStatement("UPDATE Customers SET wallet = wallet - ? WHERE username = ?");
		pst3.setDouble(1, bill);
		pst3.setString(2, cart.getUsername());
		pst3.execute();
		
		pst4 = cn.prepareStatement("DELETE FROM Cart WHERE username = ?");
		pst4.setString(1, cart.getUsername());
		pst4.execute();
		
		return ordered;
	}
}
