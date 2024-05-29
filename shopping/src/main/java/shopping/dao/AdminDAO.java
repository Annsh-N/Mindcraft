package shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import shopping.utils.DBUtils;
import shopping.pojo.Customer;
import shopping.pojo.Product;

public class AdminDAO {
	private Connection cn;
	private PreparedStatement pst1;
	private PreparedStatement pst2;
	private PreparedStatement pst3;
	private PreparedStatement pst4;
	private PreparedStatement pst5;
	private PreparedStatement pst6;
	
	public void addProduct(Product product) throws SQLException, ClassNotFoundException {
		cn = DBUtils.openConnection();
		
		pst1 = cn.prepareStatement("INSERT INTO Products VALUES(?,?,?,?,?)");
		
		pst1.setInt(1, product.getPid());
		pst1.setString(2, product.getName());
		pst1.setDouble(3, product.getPrice());
		pst1.setInt(4,  product.getQuantity());
		pst1.setDouble(5,  product.getDiscount());
		
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
	
	public void addCustomer(Customer customer) throws ClassNotFoundException, SQLException {
		cn = DBUtils.openConnection();
		
		pst3 = cn.prepareStatement("INSERT INTO Customers VALUES(?,?,?)");
		
		pst3.setString(1,  customer.getUsername());
		pst3.setString(2,  customer.getPassword());
		pst3.setDouble(3,  customer.getWallet());
		
		pst3.execute();
		
		DBUtils.closeConnection();
	}
	
	public boolean removeCustomer(String username) throws ClassNotFoundException, SQLException {
		cn = DBUtils.openConnection();
		
		pst6 = cn.prepareStatement("SELECT * FROM Customers WHERE username = ?");
		pst6.setString(1, username);
		ResultSet rs = pst6.executeQuery();
		if (rs.next()) {
			Customer customer = new Customer(username, rs.getString("password"));
			
			pst4 = cn.prepareStatement("DELETE FROM Customers WHERE username = ?");
			pst4.setString(1, customer.getUsername());
			pst4.execute();
			
			pst5 = cn.prepareStatement("DELETE FROM Cart WHERE username = ?");
			pst5.setString(1, customer.getUsername());
			pst5.execute();
			
			DBUtils.closeConnection();
			return true;
		}
		else {
			DBUtils.closeConnection();
			return false;
		}
	}
}
