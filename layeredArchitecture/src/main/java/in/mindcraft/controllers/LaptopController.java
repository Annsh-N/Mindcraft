package in.mindcraft.controllers;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import in.mindcraft.dao.LaptopDAO;
import in.mindcraft.pojo.Laptop;

@Controller
public class LaptopController {
	
	private LaptopDAO ld = new LaptopDAO();
	
	
	public ModelAndView displayModelAndView() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("index.jsp");
		
		return mv;
	}
	
	@RequestMapping("/insert")
	public ModelAndView insertLaptop(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		
		Laptop laptop = new Laptop(Integer.parseInt(request.getParameter("pid")), 
								   request.getParameter("make"), 
								   Double.parseDouble(request.getParameter("cost")));
		
		ld.addLaptop(laptop);
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("index.jsp");
		
		return mv;
	}
	
	@RequestMapping("/display")
	public ModelAndView displayLaptops(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		ModelAndView mv = new ModelAndView();
		
		List<Laptop> list = ld.getLaptop();
		
		mv.setViewName("display.jsp");
		mv.addObject("list", list);
		
		return mv;
	}
}
