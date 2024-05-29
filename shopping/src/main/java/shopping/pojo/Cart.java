package shopping.pojo;

import java.util.*;

public class Cart {
	private String username;
	private List<Product> cart;
	
	
	public Cart(String username) {
		this.username = username;
		cart = new ArrayList<Product>();
	}
	
	public Cart(String username, List<Product> cart) {
		super();
		this.username = username;
		this.cart = cart;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public List<Product> getCart() {
		return cart;
	}


	public void setCart(List<Product> cart) {
		this.cart = cart;
	}
	
	public void addProduct(Product product) {
		if(product != null) {
			cart.add(product);
		}
	}
}
