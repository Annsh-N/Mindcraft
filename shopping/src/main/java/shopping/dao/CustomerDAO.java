package shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shopping.pojo.Customer;
import shopping.pojo.Product;
import shopping.utils.DBUtils;

public class CustomerDAO {
	private Connection cn;
	private PreparedStatement pst1;
	private PreparedStatement pst2;
	private PreparedStatement pst3;
	private PreparedStatement pst4;
	private PreparedStatement pst5;
	private PreparedStatement pst6;
	private PreparedStatement pst7;
	private PreparedStatement pst8;
	
	public void addToCart(Customer customer, int pid, int quantity) throws ClassNotFoundException, SQLException {
		cn = DBUtils.openConnection();
		
		Product product = null;
		
		pst7 = cn.prepareStatement("SELECT * FROM Products WHERE pid = ?");
		pst7.setInt(1, pid);
		ResultSet res = pst7.executeQuery();
		System.out.println("pst7 executed");
		while (res.next()) {
			product = new Product(res.getInt("pid"), res.getString("name"), res.getDouble("price"),
									  quantity, res.getDouble("discount"));
			System.out.println("while statement executing");
		}
		
		System.out.println("while statement executed");
		System.out.println(customer.getUsername());
		
		try {
			pst5 = cn.prepareStatement("SELECT * FROM Cart WHERE username = ?");
			pst5.setString(1, customer.getUsername());
			ResultSet rs = pst5.executeQuery();
			
			while(rs.next()) {
				if (rs.getInt("pid") == pid) {
					pst6 = cn.prepareStatement("UPDATE Cart SET quantity = ? WHERE username = ? AND pid = ?");
					pst6.setInt(1, rs.getInt("quantity") + product.getQuantity());
					pst6.setString(2, customer.getUsername());
					pst6.setInt(3, product.getPid());
					pst6.execute();
					DBUtils.closeConnection();
					return;
				}
			}
		} catch(Exception e) {
			
		}
		
		pst1 = cn.prepareStatement("INSERT INTO Cart VALUES(?,?,?,?,?,?)");
		
		pst1.setString(1,  customer.getUsername());
		pst1.setInt(2, product.getPid());
		pst1.setString(3, product.getName());
		pst1.setDouble(4, product.getPrice());
		pst1.setInt(5,  product.getQuantity());
		pst1.setDouble(6,  product.getDiscount());
		
		pst1.execute();
		
		DBUtils.closeConnection();
	}
	
	public List<Product> displayProducts() throws ClassNotFoundException, SQLException {
		List<Product> list = new ArrayList<>();
		
		cn = DBUtils.openConnection();
		
		
		pst2 = cn.prepareStatement("SELECT * FROM Products");
		ResultSet rs = pst2.executeQuery();
		
		while(rs.next() ) {
			Product p = new Product(rs.getInt("pid"), rs.getString("name"), rs.getDouble("price"),
									rs.getInt("quantity"), rs.getDouble("discount"));
			list.add(p);
		}
		
		DBUtils.closeConnection();
		return list;
	}
	
	public void addBalance(Customer customer, Double amount) throws ClassNotFoundException, SQLException {
		cn = DBUtils.openConnection();
		
		pst3 = cn.prepareStatement("UPDATE Customers SET wallet = ? WHERE username = ?");
		pst3.setDouble(1, customer.getWallet() + amount);
		pst3.setString(2, customer.getUsername());
		pst3.execute();
		
		DBUtils.closeConnection();
	}
	
	public double displayBalance(Customer customer) throws ClassNotFoundException, SQLException {
		cn = DBUtils.openConnection();
		double balance = 0;
		
		pst8 = cn.prepareStatement("SELECT * FROM Customers WHERE username = ?");
		pst8.setString(1, customer.getUsername());
		ResultSet rs = pst8.executeQuery();
		
		if(rs.next()) {
			balance = rs.getDouble("wallet");
		}
		
		DBUtils.closeConnection();
		return balance;
	}
	
	public Customer login(String username, String password) throws ClassNotFoundException, SQLException {
		cn = DBUtils.openConnection();
		
		pst4 = cn.prepareStatement("SELECT * FROM Customers");
		ResultSet rs = pst4.executeQuery();
		
		Customer customer = null;
		
		while(rs.next()) {
			if (rs.getString("username").equals(username) && rs.getString("password").equals(password)) {
				customer = new Customer(rs.getString("username"), rs.getString("password"), rs.getDouble("wallet"));
				break;
			}
		}
		
		DBUtils.closeConnection();
		return customer;
	}
}
