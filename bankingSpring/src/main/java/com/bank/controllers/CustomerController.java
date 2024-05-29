package com.bank.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bank.dao.CustomerDAO;
import com.bank.entities.Customer;

@Controller
public class CustomerController {
	
	private CustomerDAO cd = new CustomerDAO();
	
	@RequestMapping("/customerHome")
	public ModelAndView customerHome(HttpSession session) {
		int accNo = (Integer) session.getAttribute("accNo");
		Customer customer = cd.getCustomer(accNo);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("customer-home.jsp");
		mv.addObject("balance", customer.getBalance());
		mv.addObject("name", customer.getName());
		return mv;
	}
	
	@RequestMapping("/deposit")
	public ModelAndView deposit(HttpSession session, HttpServletRequest request) {
		int accNo = (Integer) session.getAttribute("accNo");
		double amount = Double.parseDouble(request.getParameter("amountDeposit"));
		cd.deposit(accNo, amount);
		ModelAndView mv = customerHome(session);
		mv.addObject("result", "Amount Deposited Successfully!");
		return mv;
	}
	
	@RequestMapping("/withdraw")
	public ModelAndView withdraw(HttpSession session, HttpServletRequest request) {
		int accNo = (Integer) session.getAttribute("accNo");
		double amount = Double.parseDouble(request.getParameter("amountWithdraw"));
		double newBalance = cd.withdraw(accNo, amount);
		ModelAndView mv = customerHome(session);
		if (newBalance > 0) {
			mv.addObject("result", "Amount Withdrawn Successfully!");
		} else {
			mv.addObject("result", "Insufficient Balance");
		}
		return mv;
	}
	
	@RequestMapping("/transfer")
	public ModelAndView transfer(HttpSession session, HttpServletRequest request) {
		int accNo = (Integer) session.getAttribute("accNo");
		int accNo2 = Integer.parseInt(request.getParameter("accNoTransfer"));
		double amount = Double.parseDouble(request.getParameter("amountTransfer"));
		double newBalance = cd.transfer(accNo, accNo2, amount);
		ModelAndView mv = customerHome(session);
		if (newBalance == -2) {
			mv.addObject("result", "Account Not Found");
		}
		else if (newBalance == -1) {
			mv.addObject("result", "Insufficient Balance");
		} else {
			mv.addObject("result", "Amount transferred successfully!");
		}
		return mv;
	}
	
	@RequestMapping("/statement")
	public ModelAndView statement(HttpSession session) {
		int accNo = (Integer) session.getAttribute("accNo");
		String statement = cd.getStatement(accNo);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("statement.jsp");
		mv.addObject("statement", statement);
		return mv;
	}
	
}
