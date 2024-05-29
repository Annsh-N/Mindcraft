package com.bank.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bank.entities.Customer;
import com.bank.dao.CustomerDAO;

@Controller
public class LoginController {
	private CustomerDAO cd = new CustomerDAO();

	@RequestMapping("/login")
	public ModelAndView login(HttpSession session, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		int accNo = Integer.parseInt(request.getParameter("accNo"));
		if (accNo == 0) {
			mv.setViewName("admin-home.jsp");
		}
		else {
			Customer c = cd.getCustomer(accNo);
			if (c == null) {
				mv.setViewName("index.jsp");
				mv.addObject("result", "Invalid Account Number");
			}
			else {
				session.setAttribute("accNo", accNo);
				mv.setViewName("customer-home.jsp");
				mv.addObject("name", c.getName());
				mv.addObject("balance", c.getBalance());
			}
		}
		return mv;
	}
}
