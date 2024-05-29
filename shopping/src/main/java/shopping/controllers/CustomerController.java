package shopping.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import shopping.dao.CartDAO;
import shopping.dao.CustomerDAO;
import shopping.pojo.Cart;
import shopping.pojo.Customer;
import shopping.pojo.Product;

@Controller
public class CustomerController {
	CustomerDAO customerDAO = new CustomerDAO();
	CartDAO cartDAO = new CartDAO();
	
	@RequestMapping("/customerHome")
	public ModelAndView customerHome() throws ClassNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("customer.jsp");
		return mv;
	}
	
	@RequestMapping("/displayCustomerProducts")
	public ModelAndView displayCustomerProducts() throws ClassNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView();
		
		List<Product> products = customerDAO.displayProducts();
		
		mv.setViewName("customer-products.jsp");
		mv.addObject("products", products);
		return mv;
	}
	
	@RequestMapping("/addToCart")
	public ModelAndView addToCart(HttpServletRequest request, HttpSession session) throws ClassNotFoundException, SQLException {
		Customer customer = (Customer) session.getAttribute("customer");
		int pid = Integer.parseInt(request.getParameter("pid"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		customerDAO.addToCart(customer, pid, quantity);
		
		ModelAndView mv = displayCustomerProducts();
		mv.addObject("result", "Added to Cart!");
		return mv;
	}
	
	@RequestMapping("/displayBalance")
	public ModelAndView displayBalance(HttpSession session) throws ClassNotFoundException, SQLException {
		Customer customer = (Customer) session.getAttribute("customer");
		
		double balance = customerDAO.displayBalance(customer);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("balance.jsp");
		mv.addObject("balance",  balance);
		return mv;
	}
	
	@RequestMapping("/addBalance")
	public ModelAndView addBalance(HttpSession session, HttpServletRequest request) throws ClassNotFoundException, SQLException {
		Customer customer = (Customer) session.getAttribute("customer");
		
		double amount = Double.parseDouble(request.getParameter("amount"));
		
		customerDAO.addBalance(customer, amount);
		customer.addBalance(amount);
		session.setAttribute("customer", customer);
		
		ModelAndView mv = displayBalance(session);
		mv.addObject("result", amount + " added to Wallet successfully!");
		return mv;
	}

	@RequestMapping("/displayCart")
	public ModelAndView displayCart(HttpSession session) throws ClassNotFoundException, SQLException {
		Customer customer = (Customer) session.getAttribute("customer");
		
		Cart cart = cartDAO.displayCart(customer);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("cart.jsp");
		mv.addObject("cart", cart.getCart());
		return mv;
	}
	
	@RequestMapping("/displayBill")
	public ModelAndView displayBill(HttpSession session) throws ClassNotFoundException, SQLException {
		Customer customer = (Customer) session.getAttribute("customer");
		
		Cart cart = cartDAO.displayCart(customer);
		double bill = cartDAO.displayBill(cart);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bill.jsp");
		mv.addObject("bill", bill);
		return mv;
	}
	
	@RequestMapping("/payBill")
	public ModelAndView payBill(HttpSession session) throws ClassNotFoundException, SQLException {
		ModelAndView mv = displayBill(session);
		
		Customer customer = (Customer) session.getAttribute("customer");
		
		Cart cart = cartDAO.displayCart(customer);
		
		List<Product> ordered = cartDAO.payBill(cart);
		
		if (ordered == null) {
			mv.addObject("result", "Insufficient Balance in Wallet");
			mv.addObject("paid", 0);
			return mv;
		}
		else if (ordered.size() == cart.getCart().size()) {
			mv.addObject("result", "All items order succesfully!");
			mv.addObject("paid", cartDAO.displayBill(cart));
			return mv;
		}
		else {
			Cart orderedCart = new Cart(customer.getUsername(), ordered);
			List<Product> unordered = new ArrayList<>();
			for (Product p : cart.getCart()) {
				if (!(ordered.contains(p))) {
					unordered.add(p);
				}
			}
			mv.addObject("result", "Items order succesfully! Some items were not ordered due to unavailability:");
			mv.addObject("unordered", unordered);
			mv.addObject("paid", cartDAO.displayBill(orderedCart));
			return mv;
		}
	}
}
