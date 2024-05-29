package in.mindcraft;

import java.util.*;

import org.springframework.web.bind.annotation.*;

@RestController
public class MyController {
	private List<Product> list = new ArrayList<>();
	
	public MyController() {
		list.add(new Product(1, "Pen", 10));
		list.add(new Product(2, "Chips", 5));
		list.add(new Product(3, "Mug", 20));
	}
	
	@RequestMapping(value="products", method = RequestMethod.GET)
	public List<Product> getProducts() {
		return list;
	}
	
	@RequestMapping(value="products", method = RequestMethod.POST)
	public List<Product> addProduct(@RequestBody Product p) {
		list.add(p);
		return list;
	}
	
	@RequestMapping(value="products/{id}", method = RequestMethod.PUT)
	public List<Product> updateProduct(@PathVariable int id, @RequestBody Product p) {
		for (Product q : list) {
			if (q.getPid() == id) {
				q.setMake(p.getMake());
				q.setCost(p.getCost());
			}
		}
		
		return list;
	}
	
	@RequestMapping(value="products/{id}", method = RequestMethod.DELETE)
	public List<Product> deleteProduct(@PathVariable int id) {
		Product rem = null;
		for (Product q : list) {
			if (q.getPid() == id) {
				rem = q;
				break;
			}
		}
		
		if (rem != null) {
			list.remove(rem);
		}
		
		return list;
	}
	
	@RequestMapping(value="products/{id}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable int id) {
		for (Product q : list) {
			if (q.getPid() == id) {
				return q;
			}
		}
		
		return null;
	}
}
