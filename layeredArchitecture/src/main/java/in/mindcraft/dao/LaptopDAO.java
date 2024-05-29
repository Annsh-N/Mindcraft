package in.mindcraft.dao;

import in.mindcraft.pojo.Laptop;
import in.mindcraft.utils.DBUtils;

import java.sql.*;
import java.util.*;

public class LaptopDAO {
	
	private Connection cn;
	private PreparedStatement pst1;
	private PreparedStatement pst2;
	
	public void addLaptop(Laptop laptop) throws ClassNotFoundException, SQLException {
		cn = DBUtils.openConnection();
		
		pst1 = cn.prepareStatement("INSERT INTO Laptop VALUES(?,?,?)");
		
		pst1.setInt(1, laptop.getPid());
		pst1.setString(2, laptop.getMake());
		pst1.setDouble(3, laptop.getCost());
		
		pst1.execute();
		
		DBUtils.closeConnection();
	}
	
	public List<Laptop> getLaptop() throws ClassNotFoundException, SQLException {
		List<Laptop> list = new ArrayList<>();
		cn = DBUtils.openConnection();
		
		pst2 = cn.prepareStatement("SELECT * FROM Laptop");
		ResultSet rs = pst2.executeQuery();
		
		while (rs.next()) {
			Laptop laptop = new Laptop(rs.getInt("pid"), rs.getString("make"), rs.getDouble("cost"));
			list.add(laptop);
		}
		
		DBUtils.closeConnection();
		
		return list;
	}
}
