package com.bank.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bank.dao.AdminDAO;
import com.bank.entities.Customer;

@Controller
public class AdminController {
	
	private AdminDAO ad = new AdminDAO();
	
	@RequestMapping("/adminHome")
	public ModelAndView adminHome() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin-home.jsp");
		return mv;
	}
	
	@RequestMapping("/addCustomer")
	public ModelAndView addCustomer(HttpServletRequest request) {
		int accNo = Integer.parseInt(request.getParameter("accNoAdd"));
		String name = request.getParameter("nameAdd");
		Customer customer = new Customer(accNo, name, 0, null);
		ModelAndView mv = adminHome();
		if (ad.addCustomer(customer)) {
			mv.addObject("result", "Customer Added Successfully!");
		}
		else {
			mv.addObject("result", "Account Number already exists!");
		}
		return mv;
	}
	
	@RequestMapping("/searchByNo")
	public ModelAndView searchByNo(HttpServletRequest request) {
		int accNo = Integer.parseInt(request.getParameter("accNoSearch"));
		Customer customer = ad.searchByNo(accNo);
		ModelAndView mv = adminHome();
		if (customer == null) {
			mv.addObject("result", "Customer Not Found!");
		} else {
			mv.addObject("result", "Customer found:\n\n" + customer);
		}
		return mv;
	}
	
	@RequestMapping("/searchByName")
	public ModelAndView searchByName(HttpServletRequest request) {
		String name = request.getParameter("nameSearch");
		List<Customer> customers = ad.searchByName(name);
		ModelAndView mv = adminHome();
		if (customers == null) {
			mv.addObject("result", "No customers found!");
		} else {
			String s = "";
			for (Customer c : customers) {
				s += c + "\n";
			}
			mv.addObject("result", "Customers found:\n\n" + s);
		}
		return mv;
	}
	
	@RequestMapping("/modifyCustomer")
	public ModelAndView modify(HttpServletRequest request) {
		int accNo = Integer.parseInt(request.getParameter("accNoModify"));
		String name = request.getParameter("nameModify");
		double balance = Double.parseDouble(request.getParameter("balanceModify"));
		ModelAndView mv = adminHome();
		if (ad.modify(accNo, name, balance)) {
			mv.addObject("result", "Customer modified successfully!");
		} else {
			mv.addObject("result", "Customer not found");
		}
		return mv;
	}
	
	@RequestMapping("/deleteCustomer")
	public ModelAndView delete(HttpServletRequest request) {
		int accNo = Integer.parseInt(request.getParameter("accNoDelete"));
		ModelAndView mv = adminHome();
		if (ad.delete(accNo)) {
			mv.addObject("result", "Customer deleted successfully!");
		} else {
			mv.addObject("result", "Customer not found");
		}
		return mv;
	}
}
