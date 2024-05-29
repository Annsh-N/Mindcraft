package shopping.controllers;

import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import shopping.dao.AdminDAO;
import shopping.pojo.Customer;
import shopping.pojo.Product;

@Controller
public class AdminController {
	AdminDAO adminDAO = new AdminDAO();
	
	public ModelAndView displayModelAndView() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("admin.jsp");
		
		return mv;
	}
	
	@RequestMapping("/adminHome")
	public ModelAndView adminHome() throws ClassNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("admin.jsp");
		return mv;
	}
	
	@RequestMapping("/displayAdminProducts")
	public ModelAndView displayAdminProducts() throws ClassNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView();
		
		List<Product> products = adminDAO.displayProducts();
		
		mv.setViewName("admin-products.jsp");
		mv.addObject("products", products);
		return mv;
	}
	
	@RequestMapping("/addProductPage")
	public ModelAndView addProductPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("add-product.jsp");
		return mv;
	}
	
	@RequestMapping("/addCustomerPage")
	public ModelAndView addCustomerPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("add-customer.jsp");
		return mv;
	}
	
	@RequestMapping("/removeCustomerPage")
	public ModelAndView removeCustomerPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("remove-customer.jsp");
		return mv;
	}
	
	@RequestMapping("/addProduct")
	public ModelAndView addProduct(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		int pid = Integer.parseInt(request.getParameter("pid"));
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		double discount = Double.parseDouble(request.getParameter("discount"));
		
		Product product = new Product(pid, name, price, quantity, discount);
		
		adminDAO.addProduct(product);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("add-product.jsp");
		mv.addObject("result", "Product Added Successfully!");
		return mv;
	}
	
	@RequestMapping("/addCustomer")
	public ModelAndView addCustomer(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Customer customer = new Customer(username, password);
		
		adminDAO.addCustomer(customer);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("add-customer.jsp");
		mv.addObject("result", "Customer Added Successfully!");
		return mv;
	}
	
	@RequestMapping("/removeCustomer")
	public ModelAndView removeCustomer(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView();
		
		String username = request.getParameter("username");
		if (adminDAO.removeCustomer(username)) {
			mv.setViewName("remove-customer.jsp");
			mv.addObject("result", "Customer Removed Successfully!");
			return mv;
		}
		else {
			mv.setViewName("remove-customer.jsp");
			mv.addObject("result", "Customer Doesn't Exist");
			return mv;
		}
	}
	
}
