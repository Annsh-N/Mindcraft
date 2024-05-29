package shopping.controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import shopping.dao.CustomerDAO;
import shopping.pojo.Customer;

@Controller
public class HomeController {
	
	CustomerDAO customerDAO = new CustomerDAO();
	
	
	public ModelAndView displayModelAndView() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("index.jsp");
		
		return mv;
	}
	
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpSession session) throws ClassNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (username.equals("admin") && password.equals("admin")) {
			mv.setViewName("admin.jsp");
			return mv;
		}
		
		Customer customer = customerDAO.login(username, password);
		if (customer == null) {
			mv.setViewName("index.jsp");
			mv.addObject("result", "Incorrect Login Details");
			return mv;
		}
		else {
			session.setAttribute("customer", customer);
			mv.setViewName("customer.jsp");
			return mv;
		}
	}
}
