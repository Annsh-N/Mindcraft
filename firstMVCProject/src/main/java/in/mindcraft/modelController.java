package in.mindcraft;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class modelController {
	
//	public String displaymodel() {
//		return "index.jsp";
//	}
	
	public ModelAndView displayModelAndView() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("index.jsp");
		
		return mv;
	}
	
	@RequestMapping("/add")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
		
		int a = Integer.parseInt(request.getParameter("n1"));
		int b = Integer.parseInt(request.getParameter("n2"));
		
		int c = a + b;
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result.jsp");
		mv.addObject("c", c);
		
		return mv;
	}
	
	@RequestMapping("/subtract")
	public ModelAndView subtract(HttpServletRequest request, HttpServletResponse response) {
		
		int a = Integer.parseInt(request.getParameter("n1"));
		int b = Integer.parseInt(request.getParameter("n2"));
		
		int c = a - b;
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result.jsp");
		mv.addObject("c", c);
		
		return mv;
	}
	
	@RequestMapping("/multiply")
	public ModelAndView multiply(HttpServletRequest request, HttpServletResponse response) {
		
		int a = Integer.parseInt(request.getParameter("n1"));
		int b = Integer.parseInt(request.getParameter("n2"));
		
		int c = a * b;
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result.jsp");
		mv.addObject("c", c);
		
		return mv;
	}
	
	@RequestMapping("/divide")
	public ModelAndView divide(HttpServletRequest request, HttpServletResponse response) {
		
		int a = Integer.parseInt(request.getParameter("n1"));
		int b = Integer.parseInt(request.getParameter("n2"));
		
		int c = a / b;
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result.jsp");
		mv.addObject("c", c);
		
		return mv;
	}
}
